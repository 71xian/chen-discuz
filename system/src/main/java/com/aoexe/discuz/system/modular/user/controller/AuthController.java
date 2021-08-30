package com.aoexe.discuz.system.modular.user.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.core.annotion.Ignore;
import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.core.base.param.BaseParam.login;
import com.aoexe.discuz.core.base.param.BaseParam.register;
import com.aoexe.discuz.core.context.constant.ConstantContext;
import com.aoexe.discuz.core.context.constant.ConstantContextHolder;
import com.aoexe.discuz.system.core.token.Token;
import com.aoexe.discuz.system.modular.user.param.UserParam;
import com.aoexe.discuz.system.modular.user.service.IUserService;

import io.swagger.annotations.ApiOperation;

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
	public String register(@Validated(register.class) @RequestBody UserParam param) {
		return userService.register(param);
	}
	
	@PostMapping("/login")
	@Ignore
	public String login(@Validated(login.class) @RequestBody UserParam param) {
		return userService.login(param);
	}
	
	@PostMapping("/refresh-token")
	@Permission
	public Token refresh(String accessToken) {
		return null;
	}
	
	@GetMapping("/test")
	@Ignore
	@ApiOperation("最简单的测试方法")
	public void test() {
		System.out.println(ConstantContextHolder.getPasswordLength() == 6);
	}
}
