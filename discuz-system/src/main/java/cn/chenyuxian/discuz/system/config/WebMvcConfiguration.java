package cn.chenyuxian.discuz.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		.allowedMethods("POST","GET","PUT","DELETE")
		.maxAge(1800)
		.allowCredentials(true)
		.allowedHeaders("*")
		.exposedHeaders("Authorization");
	}
}
