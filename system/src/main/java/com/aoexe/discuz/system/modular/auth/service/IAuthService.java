package com.aoexe.discuz.system.modular.auth.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.aoexe.discuz.system.modular.auth.entity.LoginUser;

public interface IAuthService extends UserDetailsService {

	String login(String username, String password);
	
	void logout();
	
	void setContextAuthentication(LoginUser loginUser);
	
	Authentication getAuthentication();
	
	LoginUser getLoginUserByToken(String token);
	
}
