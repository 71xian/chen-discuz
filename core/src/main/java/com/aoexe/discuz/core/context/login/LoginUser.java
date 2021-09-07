package com.aoexe.discuz.core.context.login;

import java.util.Set;

public interface LoginUser {
	
	Long getId();

	String getUsername();
	
	Set<String> getPermissions();
}
