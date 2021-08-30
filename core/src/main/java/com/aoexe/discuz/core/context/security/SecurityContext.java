package com.aoexe.discuz.core.context.security;

import java.io.Serializable;

import com.aoexe.discuz.core.context.login.LoginContext;

public interface SecurityContext extends Serializable{

	LoginContext getLoginUser();
	
	void setLoginUser(LoginContext loginUser);
}
