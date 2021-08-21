package cn.chenyuxian.discuz.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

public class MyFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("禁止通过");
		HttpServletResponse httpServletResponse = null;
		if(response instanceof HttpServletResponse) {
			((HttpServletResponse) response).setStatus(HttpStatus.FORBIDDEN.value());
		}
	}

}
