package cn.chenyuxian.discuz.core.common;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Utils {

	public static Integer requestFrom() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = null;
		Integer requestFrom = null;
		if(requestAttributes == null) {
			System.out.println("null");
			return -1;
		}else {
			request = requestAttributes.getRequest();
		}
		
		Enumeration<String> headers = request.getHeaderNames();
		return 0;
	}
}
