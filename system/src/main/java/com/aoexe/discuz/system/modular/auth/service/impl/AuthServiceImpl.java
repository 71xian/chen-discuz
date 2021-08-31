package com.aoexe.discuz.system.modular.auth.service.impl;

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

import java.time.LocalDateTime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.util.BeanUtil;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.core.token.Token;
import com.aoexe.discuz.system.core.token.TokenUtil;
import com.aoexe.discuz.system.modular.auth.entity.LoginUser;
import com.aoexe.discuz.system.modular.auth.service.IAuthService;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.service.IUserService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Resource
	private IUserService userService;

	@Resource
	private UserCache cache;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		LoginUser loginUser = new LoginUser();
		loginUser = BeanUtil.trans(user, loginUser);
		return loginUser;
	}

	@Override
	public String login(String username, String password) {
		HttpServletRequest request = RequestUtil.getRequest();
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new BaseException(ResponseEnum.NOT_FOUND_USER);
		}
		if (!BCrypt.checkpw(password, user.getPassword())) {
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
		LoginUser loginUser = new LoginUser();
		loginUser = BeanUtil.trans(user, loginUser);
		Token token = new Token(user.getId(), user.getUsername());
		String tokenStr = TokenUtil.generateToken(token);
		cache.put(token.getUuid(), loginUser);
		user.setLastLoginIp(IpUtil.getIp(request));
		user.setLastLoginPort(request.getRemotePort());
		user.setLoginAt(LocalDateTime.now());
		userService.updateById(user);
		this.setContextAuthentication(loginUser);
		return tokenStr;
	}

	@Override
	public void logout() {
		String tokenStr = TokenUtil.getTokenString(RequestUtil.getRequest());
		Token token = TokenUtil.getToken(tokenStr);
		cache.remove(token.getUuid());
	}

	@Override
	public void setContextAuthentication(LoginUser loginUser) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginUser, null,
				loginUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public LoginUser getLoginUserByToken(String tokenStr) {
		Token token = TokenUtil.getToken(tokenStr);
		LoginUser user = cache.get(token.getUuid());
		if (user == null) {
			throw new BaseException(ResponseEnum.AUTH_INFO_HAD_EXPIRED);
		}
		cache.put(token.getUuid(), user);
		return user;
	}

}
