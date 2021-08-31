package com.aoexe.discuz.system.core.web.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.system.core.cache.GroupCache;

/**
 * 未认证用户访问授权端点,授权异常
 *
 * @author chenyuxian
 * @date 2021-08-29
 */
@Component
public class TokenEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private GroupCache cache;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String uri = request.getRequestURI();
		Set<String> apis = cache.get("1").getPermissions();
		if (apis.isEmpty()) {
			throw new BaseException(ResponseEnum.INTERNAL_ERROR);
		}
		if (!apis.contains(uri)) {
			throw new BaseException(ResponseEnum.RESOURCE_NOT_FOUND);
		}
		throw new BaseException(ResponseEnum.UNAUTHORIZED);
	}

}
