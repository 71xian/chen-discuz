package com.aoexe.discuz.web.controller;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.system.modular.group.service.IGroupsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/permission")
@Api(value = "权限管理", tags = "权限管理")
public class PermissionController {

	@Resource
	private IGroupsService groupService;

	@PostMapping("/{id}")
	@ApiOperation(value = "修改用户组权限", notes = "修改用户组权限")
	public void editPermission(@PathVariable("id") Long groupId, @RequestBody String[] permissons) {
		groupService.editPermissions(groupId, Arrays.asList(permissons));
	}

}
