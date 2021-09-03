package com.aoexe.discuz.system.core.security.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aoexe.discuz.core.annotion.Ignore;
import com.aoexe.discuz.core.annotion.Permission;
import com.aoexe.discuz.core.context.login.LoginContext;

@Component
public class SecurityFilter implements HandlerInterceptor {

	public static final List<Filter> filters = new ArrayList<>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (Objects.equals(request.getMethod(), "OPTIONS")) {
			return true;
		}

		Method method = null;
		if (handler instanceof HandlerMethod) {
			method = ((HandlerMethod) handler).getMethod();
		}
		
		if (Objects.isNull(method) || Objects.nonNull(method.getAnnotation(Ignore.class))) {
			return true;
		}

		Permission permission = method.getAnnotation(Permission.class);
		if(Objects.nonNull(permission)) {
			request.setAttribute("permission", permission.value());			
		}
		
		if(filters.isEmpty()) {
			init();
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
	
	private void init() {
		SecurityFilter.filters.add(new TokenFilter());
		SecurityFilter.filters.add(new PermissionFilter());
	}

}
