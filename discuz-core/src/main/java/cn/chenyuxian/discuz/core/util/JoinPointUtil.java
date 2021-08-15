package cn.chenyuxian.discuz.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * 切面工具
 * 
 * @author chenyuxian
 * @date 2021-08-05
 */
public class JoinPointUtil {

	/**
	 * 获取切面的参数json
	 * @param joinPoint
	 * @return
	 */
	public static String getArgsJsonString(JoinPoint joinPoint) {
		StringBuilder builder = new StringBuilder();
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (!isFilterObject(arg)) {
				if(arg != null) {
					String jsonStr = JSON.toJSONString(arg);
					builder.append(jsonStr).append(" ");
				}
			}
		}
		return builder.toString().trim();
	}

	/**
	 * 判断是否需要拼接参数，过滤掉HttpServletRequest,MultipartFile,HttpServletResponse等类型参数
	 * @param arg
	 * @return
	 */
	private static boolean isFilterObject(Object arg) {
		return arg instanceof MultipartFile || arg instanceof HttpServletRequest || arg instanceof HttpServletResponse;
	}
}
