package com.aoexe.discuz.system.core.security.core;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aoexe.discuz.core.context.login.LoginContext;
import com.aoexe.discuz.core.context.login.LoginUser;
import com.aoexe.discuz.core.token.Token;
import com.aoexe.discuz.core.util.SpringUtil;
import com.aoexe.discuz.core.util.TokenUtil;
import com.aoexe.discuz.system.core.cache.UserCache;

public class TokenFilter implements Filter {

	private UserCache cache = SpringUtil.getBean(UserCache.class);

	@Override
	public boolean filter(HttpServletRequest request, HttpServletResponse response) {
		String tokenStr = TokenUtil.getTokenString(request);
		if (Objects.nonNull(tokenStr)) {
			Token token = TokenUtil.getTokenFromStr(tokenStr);
			LoginUser user = cache.get(token.getUuid());
			if (Objects.nonNull(user)) {
				cache.put(token.getUuid(), user);
				LoginContext.set(user);
				return true;
			}
		}
		return false;
	}

}
