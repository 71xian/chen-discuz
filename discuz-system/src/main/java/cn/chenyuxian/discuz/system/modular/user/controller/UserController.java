package cn.chenyuxian.discuz.system.modular.user.controller;


import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chenyuxian.discuz.core.annotion.Ignore;
import cn.chenyuxian.discuz.core.base.param.BaseParam.login;
import cn.chenyuxian.discuz.core.base.param.BaseParam.register;
import cn.chenyuxian.discuz.core.base.token.Token;
import cn.chenyuxian.discuz.system.modular.user.param.UserParam;
import cn.chenyuxian.discuz.system.modular.user.service.IUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;
	
	@PostMapping("/register")
	@Ignore
	public String register(@Validated(register.class) @RequestBody UserParam param) {
		userService.register(param);
		return "success";
	}
	
	@PostMapping("/login")
	@Ignore
	public Token login(@Validated(login.class) @RequestBody UserParam param) {
		return userService.login(param);
	}
}
