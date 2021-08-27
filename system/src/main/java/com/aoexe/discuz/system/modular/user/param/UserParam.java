package com.aoexe.discuz.system.modular.user.param;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.aoexe.discuz.core.base.param.BaseParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserParam extends BaseParam implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "用户名不为空", groups = { register.class, login.class })
	private String username;
	
	@NotBlank(message = "用户密码不为空", groups = { register.class, login.class })
	private String password;
	
	@NotBlank(message = "用户昵称不为空", groups = { register.class })
	private String nickname;
	
}
