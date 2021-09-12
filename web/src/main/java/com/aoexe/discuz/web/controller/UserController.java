package com.aoexe.discuz.web.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.aoexe.discuz.system.core.util.MinIOUtil;
import com.aoexe.discuz.system.modular.group.model.entity.GroupUser;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.user.model.entity.DenyUser;
import com.aoexe.discuz.system.modular.user.model.entity.User;
import com.aoexe.discuz.system.modular.user.model.param.UserParam;
import com.aoexe.discuz.system.modular.user.model.result.DenyUserResult;
import com.aoexe.discuz.system.modular.user.model.result.ExcelUser;
import com.aoexe.discuz.system.modular.user.model.result.UserResult;
import com.aoexe.discuz.system.modular.user.service.IDenyUserService;
import com.aoexe.discuz.system.modular.user.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDzqGroupService groupService;
	
	@Autowired
	private IDenyUserService denyUserService;

	@Resource
	private MinIOUtil util;

	private static final String bucketName = "discuz";

	@DeleteMapping
	@ApiOperation(value = "用户批量删除", notes = "用户批量删除")
	public void deleteBatchUser(@RequestBody Long[] userIds) {
		userService.removeByUserIds(Arrays.asList(userIds));
	}

	@PostMapping("{id}/avatar")
	@ApiOperation(value = "上传头像", notes = "上传头像")
	public User uploadAvatar(@PathVariable("id") Long id, @RequestParam("avatar") MultipartFile file){
		String key = "/avatar/" + id.toString() + "/" + file.getOriginalFilename();
		User user = userService.updateAvatar(id, key);
		util.uploadObject(file, bucketName, key);
		return user;
	}

	@DeleteMapping("/{id}/avatar")
	@ApiOperation(value = "删除头像", notes = "删除头像")
	public User deleteAvatar(@PathVariable("id") Long id) {
		User user = userService.updateAvatar(id, "");
		util.removeObject(bucketName, user.getAvatar());
		return user;
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "用户资料修改", notes = "用户资料修改")
	public UserResult updateUser(@PathVariable("id") Long id, @RequestBody UserParam param) {
		return userService.updateUser(id, param);
	}

	@PatchMapping
	@ApiOperation(value = "批量修改用户用户组", notes = "批量修改用户用户组")
	public List<GroupUser> updateGroup(@RequestBody List<GroupUser> params) {
		params.forEach(p -> groupService.createGroupUser(p.getGroupId(), p.getUserId()));
		return params;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "用户资料展示", notes = "用户资料展示")
	public UserResult viewUser(@PathVariable("id") Long userId, @Nullable @RequestParam("include") String include) {
		User user = userService.getById(userId);
		UserResult result = userService.buildResult(user);
		return result;
	}

	@GetMapping
	@ApiOperation(value = "用户搜索", notes = "用户搜索")
	public IPage<User> search(HttpServletRequest request){
		return userService.search(request);
	}
	
	@GetMapping("/export")
	@ApiOperation(value = "用户资料导出", notes = "用户资料导出")
	public void export(@RequestParam("ids") Long[] userIds, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream;charset=utf-8");
		response.addHeader("Content-Disposition", "attachment;filename=user_excel.xlsx");
		List<ExcelUser> users = userService.buildExcelUser(Arrays.asList(userIds));
		EasyExcel.write(response.getOutputStream(), ExcelUser.class).sheet("sheet").doWrite(users);
	}

	@PostMapping("/{id}/deny")
	@ApiOperation(value = "拉黑用户", notes = "拉黑用户")
	public DenyUser denyUser(@PathVariable("id") Long denyUserId) {
		return denyUserService.denyUser(denyUserId);
	}

	@DeleteMapping("{id}/deny")
	@ApiOperation(value = "取消拉黑用户", notes = "取消拉黑用户")
	public void removeDenyUser(@PathVariable("id") Long denyUserId) {
		denyUserService.removeDenyUser(denyUserId);
	}

	@GetMapping("{id}/deny")
	@ApiOperation(value = "用户拉黑列表", notes = "用户拉黑列表")
	public IPage<DenyUserResult> denyUserList(@PathVariable("id") Long userId, HttpServletRequest request) {
		String current = request.getParameter("current");
		String size = request.getParameter("size");
		String sort = request.getParameter("sort");
		
		Page<DenyUserResult> pages = new Page<>();
		return denyUserService.selectPage(pages);
	}

}
