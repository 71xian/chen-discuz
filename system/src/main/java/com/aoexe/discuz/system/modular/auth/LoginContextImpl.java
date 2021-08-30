package com.aoexe.discuz.system.modular.auth;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.context.login.LoginContext;

@Component
public class LoginContextImpl implements LoginContext{

	
	
	@Override
	public Long getUserId() {
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

}
