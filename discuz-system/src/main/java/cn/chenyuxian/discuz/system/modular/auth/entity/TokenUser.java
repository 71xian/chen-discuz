package cn.chenyuxian.discuz.system.modular.auth.entity;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.chenyuxian.discuz.security.auth.SecurityUser;
import lombok.Data;

/**
 * 安全认证类
 *
 * @author chenyuxian
 * @date 2021-08-22
 */
@Data
public class TokenUser implements SecurityUser, Serializable{

	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private String role;
	
	private Set<String> permission;
	
	@Override
	@JsonFormat
	public String getPassword() {
		return password;
	}

	@Override
	public boolean match(String password) {
		return password.equals(getPassword());
	}

}
