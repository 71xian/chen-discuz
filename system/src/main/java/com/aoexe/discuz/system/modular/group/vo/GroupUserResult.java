package com.aoexe.discuz.system.modular.group.vo;

import lombok.Data;

@Data
public class GroupUserResult {
	
	private Long groupId;
	
	private Long userId;
	
	private boolean succeed;
	
	private String error;

}
