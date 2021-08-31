package com.aoexe.discuz.system.modular.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.system.config.QiniuConfiguration;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.qiniu.common.QiniuException;

/**
 * <p>
 * 前端控制器
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

	@Resource
	private QiniuConfiguration qiniu;

	@PostMapping("{id}/avatar")
	@Permission
	public String uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile file)
			throws QiniuException, UnsupportedEncodingException, IOException {
		return qiniu.upload(file.getInputStream(), id.toString() + "//" + file.getOriginalFilename());
	}
	
	@DeleteMapping("{id}/avatar")
	@Permission
	public void deleteAvatar(@PathVariable("id") Long id) {
		
	}
	
	@GetMapping("/{id}")
	public void view(@PathVariable("id") Long id, @RequestParam("include") String include) {
		
	}
	
	@GetMapping
	public void search() {
		
	}
	
	@PatchMapping("/real")
	public void real(@RequestParam("identify") String identify, @RequestParam("realname") String realname) {
		
	}
	
	@GetMapping("/export")
	public void export(@RequestParam("ids") Long[] ids) {
		
	}
}
