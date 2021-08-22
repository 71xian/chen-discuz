package cn.chenyuxian.discuz.security.auth;

import java.util.Set;

public interface SecurityUser {

	String getUsername();
	
	String getPassword();
	
	String getRole();
	
	Set<String> getPermission();
	
	boolean match(String password);
}
