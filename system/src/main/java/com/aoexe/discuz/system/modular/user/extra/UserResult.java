package com.aoexe.discuz.system.modular.user.extra;

import java.io.Serializable;

import com.aoexe.discuz.system.modular.user.entity.User;

import lombok.Data;

@Data
public class UserResult implements Serializable{

	private User user;
	
	private boolean canEdit;
	
	private boolean canDelete;
	
	private boolean canWalletPay;
	
	private String banReason;
	
	private boolean canEditUsername;
	
}
