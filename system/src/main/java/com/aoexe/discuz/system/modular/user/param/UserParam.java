package com.aoexe.discuz.system.modular.user.param;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.aoexe.discuz.core.base.param.BaseParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserParam extends BaseParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(groups = { register.class, login.class })
	@NotBlank(groups = { register.class, login.class })
	@Length(max = 15, groups = {register.class, login.class})
	private String username;

	@NotNull(groups = { register.class, login.class })
	@NotBlank(groups = { register.class, login.class })
	private String password;
	
	private String newPassword;

	@NotNull(groups = { register.class })
	@NotBlank(groups = { register.class })
	@Length(max = 15, groups = {register.class, login.class})
	private String nickname;
	
	private String password_confirmation;
	
	private String payPassword;
	
	private String pay_password_confirmation;
	
	private String pay_password_token;
	
	private String mobile;
	
	private Integer status;
	
	private String refuse_message;
	
	private String signature;
	
	private String register_reason;
}
