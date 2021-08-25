package cn.chenyuxian.discuz.core.context.security;

import java.io.Serializable;

import cn.chenyuxian.discuz.core.context.login.LoginUser;

public interface SecurityContext extends Serializable{

	LoginUser getLoginUser();
	
	void setLoginUser(LoginUser loginUser);
}
