package com.aoexe.discuz.core.token;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token {

	private Long id;
	
	private String username;
	
	private String uuid;

	// 如果没有无参构造方法，那么json转化就会失败
	public Token(Long id, String username) {
		this.id = id;
		this.username = username;
		this.uuid = UUID.randomUUID().toString();
	}
	
	
}
