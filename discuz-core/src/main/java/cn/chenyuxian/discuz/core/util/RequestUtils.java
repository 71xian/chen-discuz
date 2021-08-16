package cn.chenyuxian.discuz.core.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.chenyuxian.discuz.core.holder.RequestHolder;

/**
 * http工具
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
public class RequestUtils {

	public static String getParameters() {
		HttpServletRequest request = RequestHolder.getRequest();
		if (null == request) {
			return null;
		}
		Enumeration<String> parameters = request.getParameterNames();
		if (parameters == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		while (parameters.hasMoreElements()) {
			String parameter = parameters.nextElement();
			sb.append("&").append(parameter).append("=").append(request.getParameter(parameter));
		}
		return sb.toString();
	}

	public static Map<String, String[]> getParametersMap() {
		HttpServletRequest request = RequestHolder.getRequest();
		if (null == request) {
			return new HashMap<>();
		}
		return request.getParameterMap();
	}

	public static String getHeader(String headerName) {
		HttpServletRequest request = RequestHolder.getRequest();
		if (null == request) {
			return null;
		}
		return request.getHeader(headerName);
	}

}
