package com.aoexe.discuz.core.context.security;

import java.io.Serializable;

import com.aoexe.discuz.core.context.login.LoginUser;

public interface SecurityContext extends Serializable{

	LoginUser getLoginUser();
	
	void setLoginUser(LoginUser loginUser);
}
