package com.aoexe.discuz.system.modular.auth.context;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.login.LoginContext;
import com.aoexe.discuz.system.modular.auth.entity.LoginUser;
import com.aoexe.discuz.system.modular.auth.service.IAuthService;
import com.aoexe.discuz.system.modular.user.service.IUserService;

/**
 * 登录用户上下文实现类
 *
 * @author chenyuxian
 * @date 2021-08-31
 */
@Component
public class LoginContextImpl implements LoginContext{
	
	@Resource
	private IAuthService authService;
	
	@Resource
	private IUserService userService;
	
	private LoginContextImpl() {}
	
	@Override
	public Long getUserId() {
		return this.getLoginUser().getId();
	}

	@Override
	public String getUsername() {
		return this.getLoginUser().getUsername();
	}

	@Override
	public boolean hasLogin() {
		if(getLoginUser() == null) {
			return false;
		}
		return true;
	}

	@Override
	public Set<String> getPermissions() {
		return getLoginUser().getPermissions();
	}
	
	private LoginUser getLoginUser() {
		Authentication authentication = authService.getAuthentication();
		if(authentication == null) {
			throw new BaseException(ResponseEnum.USER_LOGIN_STATUS_NOT_NULL);
		}
		return (LoginUser) authentication.getPrincipal();
	}

}
