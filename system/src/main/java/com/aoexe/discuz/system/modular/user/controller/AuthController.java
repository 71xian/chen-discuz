package com.aoexe.discuz.system.modular.user.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.core.annotion.Ignore;
import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.core.base.param.BaseParam.login;
import com.aoexe.discuz.core.base.param.BaseParam.register;
import com.aoexe.discuz.core.base.token.Token;
import com.aoexe.discuz.system.modular.user.param.UserParam;
import com.aoexe.discuz.system.modular.user.service.IUserService;

/**
 * 用户认证模块
 *
 * @author chenyuxian
 * @date 2021-08-27
 */
@RestController
public class AuthController {

	@Resource
	private IUserService userService;
	
	@PostMapping("/register")
	@Ignore
	public Token register(@Validated(register.class) @RequestBody UserParam param) {
		return userService.register(param);
	}
	
	@PostMapping("/login")
	@Ignore
	public Token login(@Validated(login.class) @RequestBody UserParam param) {
		return userService.login(param);
	}
	
	@PostMapping("/refresh-token")
	@Permission
	public Token refresh(String accessToken) {
		return null;
	}
}
