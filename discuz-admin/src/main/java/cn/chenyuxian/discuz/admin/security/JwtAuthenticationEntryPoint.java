package cn.chenyuxian.discuz.admin.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.chenyuxian.discuz.core.response.DzqResponse;
import cn.chenyuxian.discuz.core.response.ResponseCode;

/**
 * jwt 认证进入点
 *
 * @author chenyuxian
 * @date 2021-08-16
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -1495937689225024570L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String msg = "请求访问失败:" + request.getRequestURI() + ", 认证失败，无法访问系统资源";
		response.setStatus(HttpStatus.OK.value());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSONObject.toJSONString(new DzqResponse<String>(ResponseCode.INVALID_TOKEN, msg)));
	}

}
