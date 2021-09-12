package com.aoexe.discuz.system.modular.user.model.result;

import java.io.Serializable;

import lombok.Data;

@Data
public class DenyUserResult implements Serializable{
	
	private String avatar;
	
	private Long denyUserId;
	
	private String nickname;
	
	private Long userId;
	
	private String username;
	
}
