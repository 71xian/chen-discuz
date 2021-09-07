package com.aoexe.discuz.system.modular.auth.service.impl;

import static com.aoexe.discuz.core.constant.ResponseEnum.USER_BAN;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_IN_REVIEW;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_NEED_SIGNIN_FIELDS;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_IGNORE;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_REJECT;
import static com.aoexe.discuz.system.modular.user.extra.UserStatus.BAN;
import static com.aoexe.discuz.system.modular.user.extra.UserStatus.IGNORE;
import static com.aoexe.discuz.system.modular.user.extra.UserStatus.MOD;
import static com.aoexe.discuz.system.modular.user.extra.UserStatus.NEED_FIELDS;
import static com.aoexe.discuz.system.modular.user.extra.UserStatus.REFUSE;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.session.SessionContext;
import com.aoexe.discuz.core.util.BCryptPasswordEncoder;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.system.core.cache.ConfigCache;
import com.aoexe.discuz.system.core.cache.TokenCache;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.core.util.TokenUtil;
import com.aoexe.discuz.system.modular.auth.param.LoginParam;
import com.aoexe.discuz.system.modular.auth.param.RegisterParam;
import com.aoexe.discuz.system.modular.auth.param.TokenParam;
import com.aoexe.discuz.system.modular.auth.result.AuthResult;
import com.aoexe.discuz.system.modular.auth.service.IAuthService;
import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.service.IUserService;

import io.jsonwebtoken.Claims;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements IAuthService {

	@Resource
	private IUserService userService;

	@Resource
	private BCryptPasswordEncoder encoder;

	@Resource
	private IGroupUserService groupUserService;

	@Resource
	private IDzqGroupService groupService;

	@Resource
	private UserCache userCache;

	@Resource
	private TokenCache tokenCache;
	
	@Resource
	private ConfigCache configCache;
	
	@Resource
	private TokenUtil tokenUtil;

	@Override
	public AuthResult register(RegisterParam param) {
		if (hasUser(param.getUsername())) {
			throw new BaseException(ResponseEnum.USERNAME_HAD_EXIST);
		}
		User user = new User();

		// 填充注册用户信息
		fillRegisterInfo(param, user);
		userService.save(user);

		// 用户和角色组关联
		GroupUser groupUser = new GroupUser();
		groupUser.setUserId(user.getId());
		groupUser.setGroupId(groupService.getDefaultGroup().getId());
		groupUserService.save(groupUser);

		String uuid = SessionContext.get();
		user.setPassword(null);
		user.setPayPassword(null);
		userCache.set(user.getId().toString(), uuid, user);
		String clientSecret = configCache.getSecret() + UUID.randomUUID().toString();
		AuthResult result = buildAuthResult(user, uuid, clientSecret);
		tokenCache.set(uuid, clientSecret);
		return result;
	}

	@Override
	public AuthResult login(LoginParam param) {
		User user = userService.getUserByUsername(param.getUsername());
		validUser(user);
		if (!encoder.matches(param.getPassword(), user.getPassword())) {
			throw new BaseException(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
		}

		HttpServletRequest request = RequestUtil.getRequest();
		user.setLastLoginIp(IpUtil.getIp(request));
		user.setLastLoginPort(request.getRemotePort());
		user.setLoginAt(new Date());
		userService.updateById(user);

		String uuid = SessionContext.get();
		user.setPassword(null);
		user.setPayPassword(null);
		userCache.set(user.getId().toString(), uuid, user);
		String clientSecret = configCache.getSecret() + UUID.randomUUID().toString();
		AuthResult result = buildAuthResult(user, uuid, clientSecret);
		tokenCache.set(uuid, clientSecret);
		return result;
	}

	@Override
	public void logout() {
		String accessToken = tokenUtil.getAccessToken(RequestUtil.getRequest());
		if (Objects.isNull(accessToken)) {
			return;
		}
		Claims claims = tokenUtil.getClaims(accessToken);
		userCache.remove(claims.getSubject(), claims.getId());
		tokenCache.remove(claims.getId());
	}

	@Override
	public AuthResult refresToken(TokenParam param) {
		Claims claims = tokenUtil.getRefreshClaims(param.getRefreshToken(), param.getClientSecret());
		String tokenStr = tokenCache.get(claims.getId());
		User user = userCache.get(claims.getSubject(), claims.getId());
		if (Objects.nonNull(tokenStr) && Objects.nonNull(user)) {
			tokenCache.remove(claims.getSubject());
			userCache.remove(claims.getSubject(), claims.getId());
			String uuid = SessionContext.get();
			userCache.set(claims.getSubject(), uuid, user);
			String clientSecret = uuid + configCache.getSecret();
			AuthResult result = buildAuthResult(user, uuid, clientSecret);
			tokenCache.set(uuid, clientSecret);
			return result;
		} else {
			throw new BaseException(ResponseEnum.INVALID_TOKEN);
		}
	}

	private boolean hasUser(String username) {
		return Objects.nonNull(userService.getUserByUsername(username));
	}

	private void fillRegisterInfo(RegisterParam param, User user) {
		HttpServletRequest request = RequestUtil.getRequest();
		if (request != null) {
			user.setUsername(param.getUsername());
			user.setPassword(encoder.encode(param.getPassword()));
			user.setNickname(param.getNickname());
			user.setRegisterIp(IpUtil.getIp(request));
			user.setRegisterPort(request.getRemotePort());
			user.setRegisterReason("用户密码注册");
			Date now = new Date();
			user.setCreatedAt(now);
			user.setUpdatedAt(now);
		}
	}

	private void validUser(User user) {
		if (user == null) {
			throw new BaseException(ResponseEnum.NOT_FOUND_USER);
		}
		switch (user.getStatus()) {
		case BAN:
			throw new BaseException(USER_BAN);
		case MOD:
			throw new BaseException(USER_IN_REVIEW);
		case REFUSE:
			throw new BaseException(VALIDATE_REJECT);
		case IGNORE:
			throw new BaseException(VALIDATE_IGNORE);
		case NEED_FIELDS:
			throw new BaseException(USER_NEED_SIGNIN_FIELDS);
		}
	}

	private AuthResult buildAuthResult(User user, String uuid, String clientSecret) {
		Date now = new Date();
		AuthResult result = new AuthResult();
		result.setUid(user.getId());
		result.setAvatarUrl(user.getAvatar());
		result.setUserStatus(user.getStatus());
		result.setIssuedAt(now);
		result.setMissNickname(false);
		result.setTokenType("Bearer ");
		result.setAccessToken(tokenUtil.buildAccessToken(user.getId(), uuid, now));
		result.setRefreshToken(tokenUtil.buildRefreshToken(user.getId(), uuid, now, clientSecret));
		return result;
	}

}
