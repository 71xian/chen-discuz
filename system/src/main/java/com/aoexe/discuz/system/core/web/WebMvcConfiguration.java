package com.aoexe.discuz.system.core.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aoexe.discuz.system.core.web.filter.RequestNoFilter;
import com.aoexe.discuz.system.core.web.filter.XssFilter;

/**
 * Web通用配置
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	}

	@Bean
	public FilterRegistrationBean<XssFilter> xssFilter() {
		FilterRegistrationBean<XssFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new XssFilter());
		bean.addUrlPatterns("/*");
		bean.setOrder(Integer.MIN_VALUE);
		return bean;
	}

	@Bean
	public FilterRegistrationBean<RequestNoFilter> requestNoFilter() {
		FilterRegistrationBean<RequestNoFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new RequestNoFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

}
