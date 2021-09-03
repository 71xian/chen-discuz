package com.aoexe.discuz.system.modular.user.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.base.param.BaseParam.edit;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.param.UserParam;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/user")
@Tag(name = "用户管理模块")
public class UserController {

	@Resource
	private IUserService userService;

	@Resource
	private COSClient cosClient;
	
	private static final String bucket = "cdn-1300757405";
	
	@DeleteMapping
	@Operation(summary = "用户批量删除")
	@Permission(value = "user.delete.batch")
	public void deleteBatch(@RequestBody Long[] userIds) {
		userService.removeByUserIds(userIds);
	}

	@PostMapping("{id}/avatar")
	@Operation(summary = "上传头像")
	@Permission(value = "user.avatar.upload")
	public User uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile file) throws CosServiceException, CosClientException, IOException {
		String key = "/avatar/" + id.toString() + "/" + file.getOriginalFilename();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(file.getSize());
		objectMetadata.setContentType(file.getContentType());
		PutObjectResult putObjectResult = cosClient.putObject(bucket, key, file.getInputStream(), objectMetadata);
		System.out.println(putObjectResult.getETag());
		User user = userService.getById(id);
		if (user == null) {
			throw new BaseException(ResponseEnum.NOT_FOUND_USER);
		}
		return userService.updateAvatar(user, key);
	}

	@DeleteMapping("/{id}/avatar")
	@Operation(summary = "删除用户头像")
	@Permission(value = "user.avatar.delete")
	public User deleteAvatar(@PathVariable("id") Long id) {
		User user = userService.getById(id);
		if (user == null) {
			throw new BaseException(ResponseEnum.NOT_FOUND_USER);
		}
		cosClient.deleteObject(bucket, user.getAvatar());
		return userService.deleteAvatar(user);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "用户资料修改")
	@Permission(value = "user.info.edit")
	public User editUser(@PathVariable("id") Long userId, @Validated(edit.class) @RequestBody UserParam param) {
		return userService.editUser(userId, param);
	}
}
