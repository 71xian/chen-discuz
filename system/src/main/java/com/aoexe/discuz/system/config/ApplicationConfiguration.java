package com.aoexe.discuz.system.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aoexe.discuz.core.base.crypto.PasswordEncoder;
import com.aoexe.discuz.system.core.crypto.BCryptPasswordEncoder;
import com.aoexe.discuz.system.core.crypto.BCryptPasswordEncoder.BCryptVersion;

/**
 * 系统配置容器
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Configuration
public class ApplicationConfiguration {

	private static final Map<String, String> config = new HashMap<>();
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2Y, 10);
	}

}
