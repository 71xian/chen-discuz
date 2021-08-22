package cn.chenyuxian.discuz.security.provider;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import cn.chenyuxian.discuz.core.validator.annotion.PasswordValid;
import lombok.Data;

@Data
public class LoginDto{

	@NotBlank(message = "用户名不为空")
	@Max(value = 16, message = "用户名不得过长")
	private String username;
	
	@NotEmpty(message = "密码不能为空")
	@PasswordValid(message = "密码格式不匹配")
	private String password;
}
