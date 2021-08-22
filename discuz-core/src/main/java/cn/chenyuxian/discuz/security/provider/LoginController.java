package cn.chenyuxian.discuz.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.chenyuxian.discuz.core.enums.DiscuzQCode;
import cn.chenyuxian.discuz.security.auth.SecurityUser;
import cn.chenyuxian.discuz.security.auth.UsernamePasswordService;
import cn.chenyuxian.discuz.security.exception.UsernamePasswordException;

@RestController
public class LoginController {

	@Autowired
	private UsernamePasswordService service;
	
	@PostMapping("/login")
	public SecurityUser login(@Validated @RequestBody LoginDto loginDto) {
		String username = loginDto.getUsername();
		String password = loginDto.getPassword();
		SecurityUser user = service.loadUserByUsername(username);
		if(user.match(password)) {
			return user;
		}else {
			throw new UsernamePasswordException(DiscuzQCode.USERNAME_OR_PASSWORD_ERROR);
		}
	}
}
