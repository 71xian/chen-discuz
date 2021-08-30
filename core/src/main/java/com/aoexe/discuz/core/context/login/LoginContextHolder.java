package com.aoexe.discuz.core.context.login;

import com.aoexe.discuz.core.util.SpringUtil;

public class LoginContextHolder {

	public static LoginContext me() {
		return SpringUtil.getBean(LoginContext.class);
	}
}
