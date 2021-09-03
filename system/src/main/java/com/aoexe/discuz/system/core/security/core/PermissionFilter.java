package com.aoexe.discuz.system.core.security.core;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.login.LoginContext;
import com.aoexe.discuz.core.context.login.LoginUser;

public class PermissionFilter implements Filter {

	@Override
	public boolean filter(HttpServletRequest request, HttpServletResponse response) {
		LoginUser user = LoginContext.get();
		if (Objects.isNull(user)) {
			throw new BaseException(ResponseEnum.USER_LOGIN_STATUS_NOT_NULL);
		}
		if (user.getId() == 1L) {
			return true;
		}

		if (!user.getPermissions().contains(request.getAttribute("permission"))) {
			throw new BaseException(ResponseEnum.UNAUTHORIZED);
		}
		return true;
	}

}
