package cn.chenyuxian.discuz.system.core.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.chenyuxian.discuz.core.annotion.Ignore;
import cn.chenyuxian.discuz.core.base.exception.BaseException;
import cn.chenyuxian.discuz.core.constant.ExpCode;
import cn.chenyuxian.discuz.core.util.TokenUtil;

/**
 * Token拦截器
 *
 * @author chenyuxian
 * @date 2021-08-21
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

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

		String access_token = TokenUtil.getToken(request);

		// token验证成功
		if (TokenUtil.checkToken(access_token)) {
			// token已过期
			if (TokenUtil.exipreToken(access_token)) {
				throw new BaseException(ExpCode.SESSION_TOKEN_EXPIRED);
			}
			return super.preHandle(request, response, handler);
		} else {
			throw new BaseException(ExpCode.INVALID_TOKEN);
		}
	}
}
