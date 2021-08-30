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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.util.BeanUtil;
import com.aoexe.discuz.system.core.cache.UserCache;
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
		return null;
	}

	@Override
	public String login(String username, String password) {
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
		return null;
	}

	@Override
	public String getToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

}
