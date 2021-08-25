package cn.chenyuxian.discuz.system.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("XssFilter init....");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		log.info("XssFilter destroy....");
	}

}
