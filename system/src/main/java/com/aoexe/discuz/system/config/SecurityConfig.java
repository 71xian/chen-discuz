package com.aoexe.discuz.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aoexe.discuz.core.util.BCryptPasswordEncoder;
import com.aoexe.discuz.core.util.BCryptPasswordEncoder.BCryptVersion;

/**
 * 安全配置类
 *
 * @author chenyuxian
 * @date 2021-09-02
 */
@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2Y);
	}
}
