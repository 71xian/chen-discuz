package cn.chenyuxian.discuz.security.provider;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SecurityUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String role;
	
	private List<String> permissions;

	public SecurityUser(String username, String password, String role, List<String> permissions) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.permissions = permissions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
}
