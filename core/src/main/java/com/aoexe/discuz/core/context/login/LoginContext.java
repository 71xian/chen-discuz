package com.aoexe.discuz.core.context.login;

import java.util.Set;

public interface LoginContext {
	
	Long getUserId();
	
	String getUsername();
	
	boolean hasLogin();
	
	Set<String> getPermissions();
	
}
