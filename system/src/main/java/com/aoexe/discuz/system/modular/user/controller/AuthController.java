package com.aoexe.discuz.system.modular.user.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.core.annotion.Ignore;
import com.aoexe.discuz.core.base.param.BaseParam.login;
import com.aoexe.discuz.core.base.param.BaseParam.register;
import com.aoexe.discuz.system.modular.user.param.UserParam;
import com.aoexe.discuz.system.modular.user.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 认证控制器
 *
 * @return
 * @author chenyuxian
 * @date 2021-09-01
 */
@RestController
@RequestMapping
@Tag(name = "用户认证模块")
public class AuthController {

	@Resource
	private IUserService userService;
	
	@PostMapping("/login")
	@Ignore
	@Operation(summary = "用户登录")
	public String login(@Validated(login.class) @RequestBody UserParam param) {
		return userService.login(param);
	}
	
	@PostMapping("/register")
	@Ignore
	@Operation(summary = "用户注册")
	public String register(@Validated(register.class) @RequestBody UserParam param) {
		return userService.register(param);
	}
	
	@GetMapping("/logout")
	@Ignore
	@Operation(summary = "用户注销")
	public void logout() {
		userService.logout();
	}
}
