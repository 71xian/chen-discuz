package com.aoexe.discuz.core.context.login;

import java.util.Set;

public interface LoginContext {

	Long getUserId();
	
	String getUsername();
	
	boolean hasLogin();
	
	boolean hasPermission(String uri);
	
	Set<String> getPermissions();
	
}
