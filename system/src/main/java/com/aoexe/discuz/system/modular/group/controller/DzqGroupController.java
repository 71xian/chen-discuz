package com.aoexe.discuz.system.modular.group.controller;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.system.modular.group.entity.DzqGroup;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@RestController
@RequestMapping("/group")
public class DzqGroupController {

	@Resource
	private IDzqGroupService groupService;
	
	@PostMapping
	public DzqGroup create(@Validated @RequestBody DzqGroup group) {
		return null;
	}
	
	@PostMapping("/permission")
	public void permission(@RequestParam("permissions") String[] permissions, @RequestParam("groupId") Long groupId) {
		Set<String> p = new HashSet<>();
		Collections.addAll(p, permissions);
		groupService.resetPermissions(groupId, p);
	}
}
