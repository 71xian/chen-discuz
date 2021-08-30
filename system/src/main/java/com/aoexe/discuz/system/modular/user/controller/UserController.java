package com.aoexe.discuz.system.modular.user.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.system.modular.user.service.IUserService;

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
@Permission
public class UserController {

	@Resource
	private IUserService userService;
	
	@DeleteMapping("/batch/{ids}")
	@Permission
	public void deleteBatchUser(@PathVariable("ids") Long[] ids) {
		userService.deleteBatchIds(ids);
	}
	
}
