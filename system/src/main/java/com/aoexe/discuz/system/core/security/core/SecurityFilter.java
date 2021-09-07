package com.aoexe.discuz.system.core.security.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.core.context.login.LoginContext;

/**
 * 安全过滤器
 *
 * @author chenyuxian
 * @date 2021-09-06 00:23:14
 */
public class SecurityFilter implements HandlerInterceptor {

	private static final List<Filter> filters = new ArrayList<>();

	private static final List<String> whitelist = new ArrayList<>();

	static {
		whitelist.add("/login");
		whitelist.add("/register");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (Objects.equals(request.getMethod(), "OPTIONS") || whitelist.contains(request.getRequestURI())) {
			return true;
		}

		Method method = null;
		if (handler instanceof HandlerMethod) {
			method = ((HandlerMethod) handler).getMethod();
		}

		if (Objects.isNull(method)) {
			return true;
		}
		
		Permission permission = method.getAnnotation(Permission.class);
		if (Objects.nonNull(permission)) {
			request.setAttribute("permission", permission.value());
		}
		
		if(filters.isEmpty()) {
			SecurityFilter.filters.add(new TokenFilter());
			SecurityFilter.filters.add(new PermissionFilter());
		}

		try {
			for (Filter filter : filters) {
				if (!filter.filter(request, response)) {
					return false;
				}
			}
		} finally {
			LoginContext.clear();
		}
		return true;
	}

}
