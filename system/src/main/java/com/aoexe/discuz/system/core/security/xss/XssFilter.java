package com.aoexe.discuz.system.core.security.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Xss过滤器
 *
 * @author chenyuxian
 * @date 2021-09-02
 */
@Slf4j
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("XssFilter init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("xss-filter...");
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		log.info("XssFilter destroy...");
	}

}
