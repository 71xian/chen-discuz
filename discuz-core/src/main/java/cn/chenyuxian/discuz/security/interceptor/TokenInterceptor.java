package cn.chenyuxian.discuz.security.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.chenyuxian.discuz.core.enums.DiscuzQCode;
import cn.chenyuxian.discuz.security.annotion.Ignore;
import cn.chenyuxian.discuz.security.exception.EmptyTokenException;
import cn.chenyuxian.discuz.security.exception.InValidTokenException;
import cn.chenyuxian.discuz.security.exception.TokenExpiredException;
import cn.chenyuxian.discuz.security.utils.JwtTokenUtils;

/**
 * Token拦截器
 *
 * @author chenyuxian
 * @date 2021-08-21
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JwtTokenUtils tokenUtils;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Method method = null;
		if (handler instanceof HandlerMethod) {
			method = ((HandlerMethod) handler).getMethod();
		}
		// 匿名访问方法
		if (method == null || method.getAnnotation(Ignore.class) != null) {
			return super.preHandle(request, response, handler);
		}

		String access_token = tokenUtils.getToken(request);
		
		// 未获取到token
		if (access_token == null) {
			throw new EmptyTokenException(DiscuzQCode.EMPTY_TOKEN);
		}
		
		// token验证成功
		if(tokenUtils.checkToken(access_token)) {
			// token已过期
			if(tokenUtils.exipreToken(access_token)) {
				throw new TokenExpiredException(DiscuzQCode.SESSION_TOKEN_EXPIRED);
			}
			return super.preHandle(request, response, handler);
		}else {
			throw new InValidTokenException(DiscuzQCode.INVALID_TOKEN);
		}			
	}
}
