package com.aoexe.discuz.core.context.security;

import com.aoexe.discuz.core.context.login.LoginContext;

public class SecurityContextImpl implements SecurityContext {

	private static final long serialVersionUID = 1L;
	
	private LoginContext loginUser;

	public SecurityContextImpl() {
	}

	public SecurityContextImpl(LoginContext loginUser) {
		this.loginUser = loginUser;
	}

	@Override
	public LoginContext getLoginUser() {
		return loginUser;
	}

	@Override
	public void setLoginUser(LoginContext loginUser) {
		this.loginUser = loginUser;
	}

}
