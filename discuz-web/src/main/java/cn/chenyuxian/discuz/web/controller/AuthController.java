package cn.chenyuxian.discuz.web.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.chenyuxian.discuz.core.annotion.Ignore;
import cn.chenyuxian.discuz.core.base.crypto.BCryptPasswordEncoder;
import cn.chenyuxian.discuz.core.base.param.BaseParam.add;
import cn.chenyuxian.discuz.core.context.security.SecurityContextHolder;
import cn.chenyuxian.discuz.core.context.security.SecurityContextImpl;
import cn.chenyuxian.discuz.core.util.IpUtil;
import cn.chenyuxian.discuz.core.util.RequestUtil;
import cn.chenyuxian.discuz.system.core.cache.PermissionCache;
import cn.chenyuxian.discuz.system.modular.group.entity.GroupUser;
import cn.chenyuxian.discuz.system.modular.group.service.IGroupService;
import cn.chenyuxian.discuz.system.modular.group.service.IGroupUserService;
import cn.chenyuxian.discuz.system.modular.user.entity.User;
import cn.chenyuxian.discuz.system.modular.user.param.UserParam;
import cn.chenyuxian.discuz.system.modular.user.service.IUserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AuthController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupUserService groupUserService;
	
	@Autowired
	private IGroupService groupService;
	
	@PostMapping("/register")
	@Ignore
	public void register(@Validated(value = {add.class}) @RequestBody UserParam param) {
		HttpServletRequest request = RequestUtil.getRequest();
		String ip = IpUtil.getIp(request);
		Integer port = request.getRemotePort();
		User user = new User();
		user.setUsername(param.getUsername());
		user.setPassword(passwordEncoder.encode(param.getPassword()));
		user.setNickname(param.getNickName());
		user.setLastLoginIp(ip);
		user.setLastLoginPort(port);
		user.setLoginAt(LocalDateTime.now());
		user.setRegisterIp(ip);
		user.setRegisterPort(port);
		user.setRegisterReason("用户密码注册");
		userService.save(user);
		
		GroupUser groupUser = new GroupUser();
		Long groupId = (Long) groupService.getDefaultGroup().get("id");
		String rolename = (String) groupService.getDefaultGroup().get("name");
		groupUser.setGroupId(groupId);
		groupUser.setUserId(user.getId());
		groupUserService.save(groupUser);
		user.setRole(rolename);
		user.setPermissions(PermissionCache.getPermissions(groupId));
		SecurityContextHolder.setContext(new SecurityContextImpl(user));
	}
}
