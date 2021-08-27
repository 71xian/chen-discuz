package com.aoexe.discuz.core.context.login;

import java.util.List;

public interface LoginUser {

	String getUsername();
	
	String getPassword();
	
	String getRole();
	
	List<String> getPermissions();
}
