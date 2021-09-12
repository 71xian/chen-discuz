package com.aoexe.discuz.system.modular.auth.model.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class RegisterParam implements Serializable{

	private String captchaRandStr;
	
	private String captchaTicket;
	
	private String code;
	
	@NotNull(message = "昵称不能为空")
	@Length(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String nickname;
	
	@NotNull(message = "密码不能为空")
	private String password;
	
	@NotNull(message = "密码不能为空")
	private String passwordConfirmation;
	
	@NotNull(message = "用户名不能为空")
	@Length(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String username;
}
