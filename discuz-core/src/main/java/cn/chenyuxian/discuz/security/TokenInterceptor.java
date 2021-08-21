package cn.chenyuxian.discuz.security;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import cn.chenyuxian.discuz.core.base.response.FailDiscuzQResult;
import cn.chenyuxian.discuz.core.enums.DiscuzQCode;
import cn.chenyuxian.discuz.core.utils.IpUtils;
import cn.chenyuxian.discuz.security.annotion.Ignore;
import cn.chenyuxian.discuz.security.utils.RSAUtils;

/**
 * Token拦截器
 *
 * @author chenyuxian
 * @date 2021-08-21
 */
@ConditionalOnProperty(prefix = "spring.security", name = "enabled", havingValue = "true", matchIfMissing = true )
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Method method = null;
		if (handler instanceof HandlerMethod) {
			method = ((HandlerMethod) handler).getMethod();
		}
		if (method == null || method.getAnnotation(Ignore.class) != null) {
			return super.preHandle(request, response, handler);
		}

		String access_token = request.getHeader("Authorization");
		
		if (access_token == null || !access_token.startsWith("Bearer ")) {
			FailDiscuzQResult result = FailDiscuzQResult.fail(DiscuzQCode.INVALID_TOKEN);
			Map<String, Object> extra = new HashMap<>(1); 
			extra.put("ip", IpUtils.getCityInfo(IpUtils.getIp(request)));
			result.setExtra(extra);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(result));
			out.close();
			return false;
		}
		access_token = access_token.substring("Bearer ".length());
		if(!RSAUtils.checkToken(access_token)) {
			FailDiscuzQResult result = FailDiscuzQResult.fail(DiscuzQCode.SESSION_TOKEN_EXPIRED);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(JSON.toJSONString(result));
			out.close();
			return false;
		}
		return super.preHandle(request, response, handler);			
	}
}
