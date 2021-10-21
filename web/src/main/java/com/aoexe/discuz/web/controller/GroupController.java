package com.aoexe.discuz.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aoexe.discuz.system.core.util.MinIOUtil;
import com.aoexe.discuz.system.modular.group.model.entity.Groups;
import com.aoexe.discuz.system.modular.group.model.param.GroupParam;
import com.aoexe.discuz.system.modular.group.model.result.GroupPaidResult;
import com.aoexe.discuz.system.modular.group.model.result.GroupResult;
import com.aoexe.discuz.system.modular.group.service.IGroupsService;
import com.aoexe.discuz.system.modular.group.service.IGroupPaidUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/group")
@Api(value = "用户组管理", tags = "用户组管理")
public class GroupController {

	@Autowired
	private IGroupsService groupService;

	@Autowired
	private IGroupPaidUserService groupPaidUsersService;

	@Autowired
	private MinIOUtil util;

	private static final String bucketName = "person";

	@PostMapping
	@ApiOperation(value = "添加用户组", notes = "添加用户组")
	public Groups createGroup(@RequestBody GroupParam group) {
		return groupService.create(group);
	}

	@PostMapping("{id}/icon")
	@ApiOperation(value = "上传用户组图标", notes = "上传用户组图标")
	public Groups uploadIcon(@PathVariable("id") Long groupId, @RequestParam("icon") MultipartFile file){
		String key = "/icon" + groupId.toString() + "/" + file.getOriginalFilename();
		Groups group = groupService.updateIcon(groupId, key);
		util.uploadObject(file, bucketName, key);
		return group;
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除用户组", notes = "删除用户组")
	public void removeGroup(@PathVariable("id") Long groupId) {
		groupService.remove(groupId);
	}

	@DeleteMapping
	@ApiOperation(value = "批量删除用户组", notes = "批量删除用户组")
	public Map<String, Object> removeGroups(@RequestBody Long[] groupIds) {
		Map<String, Object> map = new HashMap<>();
		for (Long id : groupIds) {
			groupService.remove(id);
			map.put("id", id);
			map.put("succeed", true);
		}
		return map;
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "修改用戶組", notes = "修改用户组")
	public Groups updateGroup(@PathVariable("id") Long groupId, @RequestBody GroupParam group) {
		return groupService.update(groupId, group);
	}

	@PatchMapping
	@ApiOperation(value = "批量修改用戶組", notes = "批量修改用戶組")
	public List<Groups> batchEditGroup(@RequestBody List<GroupParam> groups) {
		List<Groups> list = groupService.list();
		for (int i = 0; i < list.size(); i++) {
			groupService.update(list.get(i).getId(), groups.get(i));
		}
		return list;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "查询用户组", notes = "查询用户组")
	public Groups getGroup(@PathVariable("id") Long groupId) {
		return groupService.getById(groupId);
	}

	@GetMapping
	@ApiOperation(value = "获取所有用户组", notes = "获取所有用户组")
	public IPage<GroupResult> getGroups(HttpServletRequest request) {
		LambdaQueryWrapper<GroupResult> lambdaQuery = Wrappers.lambdaQuery(GroupResult.class);
		boolean isInclude = false;
		if(request.getParameter("include") != null) {
			isInclude = true;
		}
		
		if(request.getParameter("isDefault") != null) {
			lambdaQuery.eq(GroupResult::getIsDefault, 1);
		}
		
		if(request.getParameter("isPaid") == "0") {
			lambdaQuery.eq(GroupResult::getIsPaid, 0);
		}
		
		if(request.getParameter("isPaid") == "1") {
			lambdaQuery.eq(GroupResult::getIsPaid, 1);
		}
		
		Page<GroupResult> pages = new Page<>(1, 10);
		return groupService.selectPage(lambdaQuery, pages, isInclude);
	}

	@GetMapping("/paid")
	@ApiOperation(value = "获取付费用户组", notes = "获取付费用户组")
	public IPage<GroupPaidResult> getPaidGroups(HttpServletRequest request) {
		
		return groupPaidUsersService.selectPage(null, null, bucketName);
	}
}
