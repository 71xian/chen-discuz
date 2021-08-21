package cn.chenyuxian.discuz.security;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.chenyuxian.discuz.security.filter.MyFilter;

@Configuration
public class FilterConfiguration {

	//@Bean
	public FilterRegistrationBean<MyFilter> allFilter(){
		FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new MyFilter());
		bean.setOrder(-1);
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}
}
