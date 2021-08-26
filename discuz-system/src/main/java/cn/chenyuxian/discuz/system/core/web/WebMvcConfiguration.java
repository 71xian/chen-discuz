package cn.chenyuxian.discuz.system.core.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.chenyuxian.discuz.system.core.web.filter.RequestNoFilter;
import cn.chenyuxian.discuz.system.core.web.filter.XssFilter;
import cn.chenyuxian.discuz.system.core.web.interceptor.TokenInterceptor;

/**
 * Web通用配置
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.maxAge(1800)
		.allowCredentials(true)
		.allowedHeaders("*")
		.exposedHeaders("Authorization");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(new TokenInterceptor())
		.addPathPatterns("/*");
	}
	
	@Bean
	public FilterRegistrationBean<XssFilter> xssFilter(){
		FilterRegistrationBean<XssFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new XssFilter());
		bean.addUrlPatterns("/**");
		bean.setOrder(Integer.MIN_VALUE);
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<RequestNoFilter> requestNoFilter(){
		FilterRegistrationBean<RequestNoFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new RequestNoFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}
	
}
