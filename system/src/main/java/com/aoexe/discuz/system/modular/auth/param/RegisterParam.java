package com.aoexe.discuz.system.modular.auth.param;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterParam implements Serializable{

	private String captchaRandStr;
	
	private String captchaTicket;
	
	private String code;
	
	@NotBlank(message = "昵称不能为空")
	@Size(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String nickname;
	
	@NotBlank(message = "密码不能为空")
	private String password;
	
	@NotBlank(message = "密码不能为空")
	private String passwordConfirmation;
	
	@NotBlank(message = "用户名不能为空")
	@Size(min = 0, max = 15, message = "用户名或昵称不得超过15个字符")
	private String username;
}
