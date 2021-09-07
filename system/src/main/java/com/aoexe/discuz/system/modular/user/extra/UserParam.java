package com.aoexe.discuz.system.modular.user.extra;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserParam implements Serializable {

	@Size(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String nickname;
	
	@Size(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String username;
	
	private String signature;
	
	private String password;

	private String newPassword;

	private String passwordConfirmation;
	
	@Size(min = 6, max = 6, message = "支付密码为6位数")
	private String payPassword;

	@Size(min = 6, max = 6, message = "支付密码为6位数")
	private String payPasswordConfirmation;

	private String payPasswordToken;

	private String mobile;

	private Integer status;

	private String refuse_message;

	private String register_reason;

}
