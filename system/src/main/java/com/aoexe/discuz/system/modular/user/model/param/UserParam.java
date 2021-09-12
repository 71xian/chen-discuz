package com.aoexe.discuz.system.modular.user.model.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserParam implements Serializable {

	@Length(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String nickname;
	
	@Length(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String username;
	
	private String signature;
	
	private String password;

	private String newPassword;

	private String passwordConfirmation;
	
	@Length(min = 6, max = 6, message = "支付密码为6位数")
	private String payPassword;

	@Length(min = 6, max = 6, message = "支付密码为6位数")
	private String payPasswordConfirmation;

	private String payPasswordToken;

	private String mobile;

	private Integer status;

	private String refuse_message;

	private String register_reason;

}
