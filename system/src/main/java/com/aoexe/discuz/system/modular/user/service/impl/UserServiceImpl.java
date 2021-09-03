package com.aoexe.discuz.system.modular.user.service.impl;

import static com.aoexe.discuz.core.constant.ResponseEnum.USER_BAN;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_IN_REVIEW;
import static com.aoexe.discuz.core.constant.ResponseEnum.USER_NEED_SIGNIN_FIELDS;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_IGNORE;
import static com.aoexe.discuz.core.constant.ResponseEnum.VALIDATE_REJECT;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.BAN;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.IGNORE;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.MOD;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.NEED_FIELDS;
import static com.aoexe.discuz.system.modular.user.consts.UserStatus.REFUSE;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.login.LoginContext;
import com.aoexe.discuz.core.token.Token;
import com.aoexe.discuz.core.util.BCryptPasswordEncoder;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.core.util.TokenUtil;
import com.aoexe.discuz.system.core.cache.GroupCache;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.group.service.IGroupPermissionService;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.mapper.UserMapper;
import com.aoexe.discuz.system.modular.user.param.UserParam;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Resource
	private BCryptPasswordEncoder encoder;

	@Resource
	private UserMapper mapper;

	@Resource
	private IGroupUserService groupUserService;

	@Resource
	private IDzqGroupService groupService;

	@Resource
	private IGroupPermissionService groupPermissionService;

	@Resource
	private GroupCache groupCache;

	@Resource
	private UserCache userCache;

	@Override
	public String login(UserParam param) {
		User user = getUserByUsername(param.getUsername());
		String password = param.getPassword();
		if (validUser(user, password)) {
			fillLoginUserInfo(user);
			updateById(user);
			Token token = new Token(user.getId(), user.getUsername());
			String tokenStr = TokenUtil.generateToken(token);
			user.setPermissions(groupService.getPermissionsByUserId(user.getId()));
			userCache.put(token.getUuid(), user);
			return tokenStr;
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		return mapper.findByColumnStr("username", username);
	}

	@Override
	public void removeByUserIds(Long[] userIds) {
		for (Long id : userIds) {
			if (id == 1L)
				throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		mapper.removeByColumns("id", arrayToStr(userIds));
		groupUserService.removeByUserIds(userIds);
	}

	private String arrayToStr(Long[] values) {
		if (values.length == 0) {
			log.error(">>>传入的数组长度为0");
			throw new BaseException(ResponseEnum.DB_ERROR);
		}
		Set<Long> set = new HashSet<>();
		Collections.addAll(set, values);
		String str = set.stream().map(s -> s.toString()).collect(Collectors.joining(","));
		return "(" + str + ")";
	}

	@Override
	public String register(UserParam param) {
		if (getUserByUsername(param.getUsername()) != null) {
			throw new BaseException(ResponseEnum.USERNAME_HAD_EXIST);
		}
		User user = new User();
		fillRegisterUserInfo(param, user);
		save(user);
		GroupUser groupUser = new GroupUser();
		groupUser.setUserId(user.getId());
		groupUser.setGroupId(groupService.getDefaultGroup().getId());
		groupUserService.save(groupUser);
		Token token = new Token(user.getId(), user.getUsername());
		return TokenUtil.generateToken(token);
	}

	@Override
	public void logout() {
		if (Objects.nonNull(LoginContext.get())) {
			String tokenStr = TokenUtil.getTokenString(RequestUtil.getRequest());
			Token token = TokenUtil.getTokenFromStr(tokenStr);
			LoginContext.clear();
			userCache.remove(token.getUuid());
		}
	}

	@Override
	public User updateAvatar(User user, String avatarUrl) {
		user.setAvatar(avatarUrl);
		Date now = new Date();
		user.setAvatarAt(now);
		user.setUpdatedAt(now);
		updateById(user);
		return user;
	}

	@Override
	public User deleteAvatar(User user) {
		return updateAvatar(user, "");
	}

	@Override
	public User editUser(Long userId, UserParam param) {
		if(LoginContext.get().getId() == userId) {
			if(StringUtils.isEmpty(param.getPassword())) {
				throw new BaseException(ResponseEnum.PASSWORD_ILLEGALITY);
			}
		}
		if(param.getNewPassword().contains(" ")) {
			throw new BaseException(ResponseEnum.PASSWORD_NOT_ALLOW_HAS_SPACE);
		}
		if(!param.getNewPassword().equals(param.getPassword_confirmation())) {
			throw new BaseException(ResponseEnum.PASSWORD_ILLEGALITY.getCode(), "密码不相同");
		}
		return null;
	}
	
	private void fillLoginUserInfo(User user) {
		HttpServletRequest request = RequestUtil.getRequest();
		if (request != null) {
			user.setLastLoginIp(IpUtil.getIp(request));
			user.setLastLoginPort(request.getRemotePort());
			user.setLoginAt(new Date());
		}
	}

	private void fillRegisterUserInfo(UserParam param, User user) {
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

	
	private boolean validUser(User user, String password) {
		if (user == null) {
			throw new BaseException(ResponseEnum.NOT_FOUND_USER);
		}
		if (!encoder.matches(password, user.getPassword())) {
			throw new BaseException(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
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
		return true;
	}

}
