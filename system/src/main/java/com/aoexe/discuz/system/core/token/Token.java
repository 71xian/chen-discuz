package com.aoexe.discuz.system.core.token;

import lombok.Data;

@Data
public class Token {

	private Long id;
	
	private String username;

	public Token(Long id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	
	
}
