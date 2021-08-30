package com.aoexe.discuz.system.modular.auth.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService extends UserDetailsService {

	String login(String username, String password);
	
	String getToken(HttpServletRequest request);
	
	void logout();
	
	
}
