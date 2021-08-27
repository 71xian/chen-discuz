package com.aoexe.discuz.system.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 对请求生成唯一编码
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
public class RequestNoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("request-no filter init...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filtering  ....");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("request-no filter destroy...");
	}

}
