package com.aoexe.discuz.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
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

import com.alibaba.excel.EasyExcel;
import com.aoexe.discuz.system.core.util.CosUtil;
import com.aoexe.discuz.system.modular.group.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.service.IGroupUserService;
import com.aoexe.discuz.system.modular.group.vo.GroupUserResult;
import com.aoexe.discuz.system.modular.user.entity.DenyUser;
import com.aoexe.discuz.system.modular.user.entity.ExcelUser;
import com.aoexe.discuz.system.modular.user.entity.User;
import com.aoexe.discuz.system.modular.user.extra.UserParam;
import com.aoexe.discuz.system.modular.user.extra.UserResult;
import com.aoexe.discuz.system.modular.user.service.IDenyUserService;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Api(value = "用户管理", tags = "用户管理")
public class UserController {

	@Resource
	private IUserService userService;

	@Resource
	private IGroupUserService groupUserService;

	@Resource
	private IDenyUserService denyUserService;

	@Resource
	private CosUtil cosUtil;

	private static final String bucketName = "cdn-1300757405";

	@DeleteMapping
	@ApiOperation(value = "用户批量删除", notes = "用户批量删除")
	public void deleteBatchUser(@RequestBody List<Long> userIds) {
		userService.removeByUserIds(userIds);
	}

	@PostMapping("{id}/avatar")
	@ApiOperation(value = "上传头像", notes = "上传头像")
	public User uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile file)
			throws CosServiceException, CosClientException, IOException {
		String key = "/avatar/" + id.toString() + "/" + file.getOriginalFilename();
		User user = userService.updateAvatar(id, key);
		cosUtil.uploadObject(bucketName, key, file);
		return user;
	}

	@DeleteMapping("/{id}/avatar")
	@ApiOperation(value = "删除头像", notes = "删除头像")
	public User deleteAvatar(@PathVariable("id") Long id) {
		User user = userService.updateAvatar(id, "");
		cosUtil.deleteObject(bucketName, user.getAvatar());
		return user;
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "用户资料修改", notes = "用户资料修改")
	public UserResult updateUser(@PathVariable("id") Long id, @RequestBody UserParam param) {
		return userService.updateUser(id, param);
	}

	@PatchMapping
	@ApiOperation(value = "批量修改用户用户组", notes = "批量修改用户用户组")
	public List<GroupUserResult> updateGroup(@RequestBody List<GroupUser> groupUsers) {
		return groupUserService.updateGroup(groupUsers);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "用户资料展示", notes = "用户资料展示")
	public UserResult viewUser(@PathVariable("id") Long userId, @Nullable @RequestParam("include") String include) {
		return userService.viewUser(userId);
	}

	@GetMapping
	@ApiOperation(value = "用户搜索", notes = "用户搜索")
	public void search(HttpServletRequest request) {
		
	}
	
	@GetMapping("/export")
	@ApiOperation(value = "用户资料导出", notes = "用户资料导出")
	public void export(@RequestParam("ids") Long[] userIds, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=user_excel.xlsx");
		List<ExcelUser> users = userService.buildExcelUser(userIds);
		EasyExcel.write(response.getOutputStream(), ExcelUser.class).sheet("sheet").doWrite(users);
	}

	@PostMapping("/{id}/deny")
	@ApiOperation(value = "拉黑用户", notes = "拉黑用户")
	public DenyUser denyUser(@PathVariable("id") Long userId) {
		return denyUserService.denyUser(userId);
	}

	@DeleteMapping("{id}/deny")
	@ApiOperation(value = "取消拉黑用户", notes = "取消拉黑用户")
	public void removeDenyUser(@PathVariable("id") Long userId) {
		denyUserService.removeDenyUser(userId);
	}

	@GetMapping("{id}/deny")
	@ApiOperation(value = "用户拉黑列表", notes = "用户拉黑列表")
	public DenyUser denyUserList(@PathVariable("id") Long userId) {
		return null;
	}

}
