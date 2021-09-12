package com.aoexe.discuz.core.context.login;

import java.util.List;

public interface LoginUser {
	
	Long getId();

	String getUsername();
	
	List<String> getPermissions();
}
