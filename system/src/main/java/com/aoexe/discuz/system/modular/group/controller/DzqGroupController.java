package com.aoexe.discuz.system.modular.group.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/group")
@Tag(name = "用户组管理模块")
public class DzqGroupController {

	@Resource
	private IDzqGroupService groupService;

	/**
	 * 修改用户组权限
	 *
	 * @param permissions 权限集合
	 * @param groupId     被修改的用户组id
	 * @author chenyuxian
	 * @date 2021-09-02
	 */
	@Operation(summary = "修改用户组权限")
	@PostMapping("/permission/{groupId}")
	@Parameters({ @Parameter(name = "permissions", required = true) })
	public void permission(@RequestBody String[] permissions, @PathVariable("groupId") Long groupId) {
		Set<String> p = new HashSet<>();
		Collections.addAll(p, permissions);
		groupService.resetPermissions(groupId, p);
	}
}
