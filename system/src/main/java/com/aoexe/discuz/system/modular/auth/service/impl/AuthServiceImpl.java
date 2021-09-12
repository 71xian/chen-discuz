package com.aoexe.discuz.system.modular.auth.service.impl;

import static com.aoexe.discuz.core.constant.ResponseEnum.USER_BAN;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_IN_REVIEW;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_NEED_SIGNIN_FIELDS;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_IGNORE;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_REJECT;
import static com.aoexe.discuz.system.modular.user.model.enums.UserStatus.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.session.SessionContext;
import com.aoexe.discuz.core.util.BCryptPasswordEncoder;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RedisUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.core.util.TokenUtil;
import com.aoexe.discuz.system.modular.auth.model.param.LoginParam;
import com.aoexe.discuz.system.modular.auth.model.param.RegisterParam;
import com.aoexe.discuz.system.modular.auth.model.param.TokenParam;
import com.aoexe.discuz.system.modular.auth.model.result.AuthResult;
import com.aoexe.discuz.system.modular.auth.service.IAuthService;
import com.aoexe.discuz.system.modular.config.service.IConfigService;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.user.model.entity.User;
import com.aoexe.discuz.system.modular.user.service.IUserService;

import io.jsonwebtoken.Claims;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private IUserService userService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private IDzqGroupService groupService;

	@Autowired
	private IConfigService configService;

	@Autowired
	private UserCache userCache;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private RedisUtil redisUtil;

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
		groupService.createGroupUser(Long.valueOf(configService.getValueByKey("group_id")), user.getId());

		String uuid = SessionContext.get();
		user.setPassword(null);
		user.setPayPassword(null);
		userCache.hset(user.getId().toString(), uuid, user);
		String clientSecret = configService.getValueByKey("site_secret") + UUID.randomUUID().toString();
		AuthResult result = buildAuthResult(user, uuid, clientSecret);
		redisUtil.set(uuid, clientSecret, 7L, TimeUnit.DAYS);
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
		user.setLoginAt(LocalDateTime.now());
		userService.updateById(user);

		String uuid = SessionContext.get();
		user.setPassword(null);
		user.setPayPassword(null);
		userCache.hset(user.getId().toString(), uuid, user);
		String clientSecret = configService.getValueByKey("site_secret") + UUID.randomUUID().toString();
		AuthResult result = buildAuthResult(user, uuid, clientSecret);
		redisUtil.set(uuid, clientSecret, 7L, TimeUnit.DAYS);
		return result;
	}

	@Override
	public void logout() {
		String accessToken = tokenUtil.getAccessToken(RequestUtil.getRequest());
		if (Objects.isNull(accessToken)) {
			return;
		}
		Claims claims = tokenUtil.getClaims(accessToken);
		userCache.hremove(claims.getSubject(), claims.getId());
		redisUtil.remove(claims.getId());
	}

	@Override
	public AuthResult refresToken(TokenParam param) {
		Claims claims = tokenUtil.getRefreshClaims(param.getRefreshToken(), param.getClientSecret());
		String tokenStr = redisUtil.get(claims.getId());
		User user = userCache.hget(claims.getSubject(), claims.getId());
		if (Objects.nonNull(tokenStr) && Objects.nonNull(user)) {
			redisUtil.remove(claims.getId());
			userCache.hremove(claims.getSubject(), claims.getId());
			String uuid = SessionContext.get();
			userCache.hset(claims.getSubject(), uuid, user);
			String clientSecret = uuid + configService.getValueByKey("site_secret");
			AuthResult result = buildAuthResult(user, uuid, clientSecret);
			redisUtil.set(uuid, clientSecret, 7L, TimeUnit.DAYS);
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
			LocalDateTime now = LocalDateTime.now();
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
