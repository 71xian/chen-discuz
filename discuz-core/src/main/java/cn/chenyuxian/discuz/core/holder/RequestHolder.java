package cn.chenyuxian.discuz.core.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求容器
 *
 * @author chenyuxian
 * @date 2021-08-15
 */
@Slf4j
public class RequestHolder {

	/**
	 * 获取当前request
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static HttpServletRequest getRequest() {
		log.debug("getRequest -- Thread id : {}, name: {}", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (null == servletRequestAttributes) {
			return null;
		}
		return servletRequestAttributes.getRequest();
	}

	/**
	 * 获取当前Response
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static HttpServletResponse getResponse() {
		log.debug("getResponse -- Thread id :{}, name : {}", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes());
		if (null == servletRequestAttributes) {
			return null;
		}
		return servletRequestAttributes.getResponse();
	}

	/**
	 * 获取当前session
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static HttpSession getSession() {
		log.debug("getSession -- Thread id :{}, name : {}", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		HttpServletRequest request = getRequest();
		if (null == request) {
			return null;
		}
		return request.getSession();
	}

	/**
	 * 获取session的attribute
	 *
	 * @param name
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static Object getSession(String name) {
		log.debug("getSession -- Thread id :{}, name : {}", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes());
		if (null == servletRequestAttributes) {
			return null;
		}
		return servletRequestAttributes.getAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * 添加session
	 *
	 * @param name
	 * @param value
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static void setSession(String name, Object value) {
		log.debug("setSession -- Thread id :{}, name : {}", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes());
		if (null == servletRequestAttributes) {
			return;
		}
		servletRequestAttributes.setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * 清除指定session
	 *
	 * @param name
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static void removeSession(String name) {
		log.debug("removeSession -- Thread id :{}, name : {}", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes());
		if (null == servletRequestAttributes) {
			return;
		}
		servletRequestAttributes.removeAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * 获取所有session key
	 *
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public static String[] getSessionKeys() {
		log.debug("getSessionKeys -- Thread id :{}, name : {}", Thread.currentThread().getId(),
				Thread.currentThread().getName());
		ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes());
		if (null == servletRequestAttributes) {
			return null;
		}
		return servletRequestAttributes.getAttributeNames(RequestAttributes.SCOPE_SESSION);
	}
	
}
