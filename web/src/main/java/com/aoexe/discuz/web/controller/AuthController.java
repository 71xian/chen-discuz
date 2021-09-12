package com.aoexe.discuz.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.system.modular.auth.model.param.LoginParam;
import com.aoexe.discuz.system.modular.auth.model.param.RegisterParam;
import com.aoexe.discuz.system.modular.auth.model.param.TokenParam;
import com.aoexe.discuz.system.modular.auth.model.result.AuthResult;
import com.aoexe.discuz.system.modular.auth.service.IAuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 认证控制器
 *
 * @return
 * @author chenyuxian
 * @date 2021-09-01
 */
@RestController
@Api(value = "用户认证", tags = "用户认证")
public class AuthController {

	@Resource
	private IAuthService authService;
	
	@PostMapping("/login")
	@ApiOperation(value = "登录", notes = "登录")
	public AuthResult login(@RequestBody LoginParam param) {
		return authService.login(param);
	}
	
	@PostMapping("/register")
	@ApiOperation(value = "注册", notes = "注册")
	public AuthResult register(@RequestBody RegisterParam param) {
		if(param.getPassword().contains(" ")) {
			throw new BaseException(ResponseEnum.PASSWORD_NOT_ALLOW_HAS_SPACE);
		}
		return authService.register(param);
	}
	
	@GetMapping("/logout")
	@ApiOperation(value = "注销", notes = "注销")
	public void logout() {
		authService.logout();
	}
	
	@PostMapping("/refresh-token")
	@ApiOperation(value = "刷新token", notes= "刷新token")
	public AuthResult refreshToken(@RequestBody TokenParam param) {
		return authService.refresToken(param);
	}
	
}
