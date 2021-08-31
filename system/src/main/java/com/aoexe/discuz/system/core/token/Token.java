package com.aoexe.discuz.system.core.token;

import java.util.UUID;

import lombok.Data;

@Data
public class Token {

	private Long id;
	
	private String username;
	
	private String uuid;

	public Token(Long id, String username) {
		this.id = id;
		this.username = username;
		this.uuid = UUID.randomUUID().toString();
	}
	
	
}
