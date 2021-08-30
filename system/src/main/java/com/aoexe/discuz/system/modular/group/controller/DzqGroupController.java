package com.aoexe.discuz.system.modular.group.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.core.annotion.Permission;
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

	@Autowired
	private IDzqGroupService groupService;
	
	@PostMapping("/c")
	@Permission
	public void create(@Validated @RequestBody DzqGroup group) {
		
	}
	
	@GetMapping("/r/{groupId}")
	@Permission
	public DzqGroup read(@PathVariable("groupId") Long groupId) {
		return groupService.readGroup(groupId);
	}
	
	@PutMapping("/u")
	@Permission
	public void update(@Validated @RequestBody DzqGroup group) {
		
	}
	
	@DeleteMapping("/d/{groupId}")
	@Permission
	public void delete(Long groupIds) {
		
	}
}
