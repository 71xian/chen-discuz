package com.aoexe.discuz.system.core.security.core;

import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.context.login.LoginContext;
import com.aoexe.discuz.core.context.session.SessionContext;
import com.aoexe.discuz.core.util.SpringUtil;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.core.util.TokenUtil;
import com.aoexe.discuz.system.modular.group.service.IDzqGroupService;
import com.aoexe.discuz.system.modular.group.service.impl.DzqGroupServiceImpl;
import com.aoexe.discuz.system.modular.user.entity.User;

import io.jsonwebtoken.Claims;

/**
 * Token校验
 *
 * @author chenyuxian
 * @date 2021-09-06 00:27:44
 */
public class TokenFilter implements Filter {

	private UserCache userCache = SpringUtil.getBean(UserCache.class);

	private IDzqGroupService groupService = SpringUtil.getBean(DzqGroupServiceImpl.class);

	private TokenUtil tokenUtil = SpringUtil.getBean(TokenUtil.class);
	
	@Override
	public boolean filter(HttpServletRequest request, HttpServletResponse response) {
		String accessToken = tokenUtil.getAccessToken(request);
		if (Objects.nonNull(accessToken)) {
			Claims claims = tokenUtil.getClaims(accessToken);
			User user = userCache.get(claims.getSubject(), claims.getId());
			if (Objects.nonNull(user)) {
				Set<String> permissions = groupService.getPermissionsByUserId(user.getId());
				user.setPermissions(permissions);
				LoginContext.set(user);
				SessionContext.set(claims.getId());
			} else {
				throw new BaseException(ResponseEnum.INVALID_TOKEN);
			}
			return true;
		}
		return false;
	}

}
