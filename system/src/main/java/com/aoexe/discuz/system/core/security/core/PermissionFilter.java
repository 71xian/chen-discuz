package com.aoexe.discuz.system.core.security.core;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.login.LoginContext;
import com.aoexe.discuz.core.context.login.LoginUser;

/**
 * 权限校验
 *
 * @author chenyuxian
 * @date 2021-09-06 00:14:46
 */
public class PermissionFilter implements Filter {

	@Override
	public boolean filter(HttpServletRequest request, HttpServletResponse response) {
		String permission = (String) request.getAttribute("permission");
		if (Objects.isNull(permission) || permission.equals("null")) {
			return true;
		}
		
		LoginUser user = LoginContext.get();
		
		if (!user.getPermissions().contains(permission)) {
			throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		return true;
	}

}
