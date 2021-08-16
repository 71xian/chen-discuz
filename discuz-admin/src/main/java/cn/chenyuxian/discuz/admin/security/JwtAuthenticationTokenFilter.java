package cn.chenyuxian.discuz.admin.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * Jwt认证过滤器
 *
 * @author chenyuxian
 * @date 2021-08-16
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Resource
	private UserDetailsService userDetailsService;
	
	@Autowired
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if(token != null && token.startsWith("Bearer ")) {
			log.error("传递过来的token为: {}", token);
			token = token.substring("Bearer ".length());
		}
	}

}
