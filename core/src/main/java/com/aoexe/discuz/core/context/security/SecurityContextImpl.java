package com.aoexe.discuz.core.context.security;

import com.aoexe.discuz.core.context.login.LoginUser;

public class SecurityContextImpl implements SecurityContext {

	private static final long serialVersionUID = 1L;
	
	private LoginUser loginUser;

	public SecurityContextImpl() {
	}

	public SecurityContextImpl(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	@Override
	public LoginUser getLoginUser() {
		return loginUser;
	}

	@Override
	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

}
