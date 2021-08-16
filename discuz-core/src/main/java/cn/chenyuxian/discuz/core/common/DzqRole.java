package cn.chenyuxian.discuz.core.common;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DzqRole implements GrantedAuthority {

	private Integer id;
	
	private String roleName;
	
	private String roleDesc;
	
	@JsonIgnore
	@Override
	public String getAuthority() {
		return roleName;
	}

}
