package com.aoexe.discuz.core.base.token;

import java.util.Collection;
import java.util.List;

public interface TokenStore {

	String getTokenKey();
	
	void setTokenKey(String tokenKey);
	
	Token createToken(String username);
	
	Token createToken(String username, List<String> permissions, String role);
	
	Token refreshToken(String refresh_token);
	
	int storeToken(Token token);
	
	Token findToken(String username, String access_token);
	
	List<Token> findTokensByUsername(String username);
	
	Token findRefreshToken(String username, String refresh_token);
	
	int removeToken(String username, String access_token);
	
	int removeTokensByUsername(String username);
	
	int updateRolesByUsername(String username, Collection<String> roles);
	
	int updatePermissionByUsername(String username, List<String> permissions);
	
	String findRoleByUsername(String username);
	
	List<String> findPermissionsByUsername(String username, Token token);
	
	void setMaxToken(Integer maxToken);
	
	
	
}
