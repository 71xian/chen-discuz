package com.aoexe.discuz.system.modular.auth.model.param;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TokenParam implements Serializable{

	@NotBlank
	private String refreshToken;
	
	@NotBlank
	private String grantType;
	
	private List<String> scope;
	
	private String clientSecret;
	
	private Long clientId;
}
