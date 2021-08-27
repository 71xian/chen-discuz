package com.aoexe.discuz.system.core.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aoexe.discuz.core.annotion.Ignore;
import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ErrorCode;
import com.aoexe.discuz.core.util.TokenUtil;

/**
 * Token拦截器
 *
 * @author chenyuxian
 * @date 2021-08-21
 */
public class TokenInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		Method method = null;
		if (handler instanceof HandlerMethod) {
			method = ((HandlerMethod) handler).getMethod();
		}
		
		if (method == null || method.getAnnotation(Ignore.class) != null) {
			return true;
		}

		String access_token = TokenUtil.getToken(request);

		// token验证成功
		if (TokenUtil.checkToken(access_token)) {
			// token已过期
			if (TokenUtil.exipreToken(access_token)) {
				throw new BaseException(ErrorCode.SESSION_TOKEN_EXPIRED);
			}
			return true;
		} else {
			throw new BaseException(ErrorCode.INVALID_TOKEN);
		}
	}
}
