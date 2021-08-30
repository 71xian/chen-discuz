package com.aoexe.discuz.system.modular.auth.factory;

import java.time.LocalDateTime;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.core.util.SpringUtil;
import com.aoexe.discuz.system.modular.auth.entity.LoginUser;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;

/**
 * 登录用户工厂类
 *
 * @author chenyuxian
 * @date 2021-08-30
 */
public class LoginFactory {

	// 为什么要这么做呢，因为静态方法无法使用注入，所以直接通过ApplicationContext直接获取
	private static final IDzqGroupService groupService = SpringUtil.getBean(IDzqGroupService.class);
	
	
	/**
	 * 填充登录用户相关信息
	 *
	 * @param loginUser
	 * @author chenyuxian
	 * @date 2021-08-30
	 */
	public static void fillLoginUserInfo(LoginUser loginUser) {
		HttpServletRequest request = RequestUtil.getRequest();
		if(request != null) {
			loginUser.setLastLoginIp(IpUtil.getIp(request));
			loginUser.setLastLoginPort(request.getRemotePort());
			loginUser.setLoginAt(LocalDateTime.now());
			
			Long userId = loginUser.getId();
			
			Set<String> roles = 
			Set<String> permission = groupService.getPermissionByUserId(userId);
			loginUser.setPermissions(permission);
			
		}
	}
}
