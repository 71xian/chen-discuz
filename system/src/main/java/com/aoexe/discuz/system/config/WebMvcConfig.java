package com.aoexe.discuz.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aoexe.discuz.system.core.security.core.SecurityFilter;

/**
 * Web通用配置
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SecurityFilter()).addPathPatterns("/**").excludePathPatterns("/druid/**", "/webjars/**",
				"/swagger-ui.html", "/swagger-ui/*", "/v2/api-docs", "/v3/api-docs", "/swagger-resources/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowCredentials(true).allowedHeaders("*").allowedMethods("*").allowedOrigins("*")
				.exposedHeaders("Authorization");
	}

}
