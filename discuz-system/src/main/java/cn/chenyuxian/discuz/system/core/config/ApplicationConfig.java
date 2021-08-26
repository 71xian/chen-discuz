package cn.chenyuxian.discuz.system.core.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.chenyuxian.discuz.core.base.crypto.BCryptPasswordEncoder;
import cn.chenyuxian.discuz.core.base.crypto.BCryptPasswordEncoder.BCryptVersion;

/**
 * 系统配置容器
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Configuration
public class ApplicationConfig {

	private static final Map<String, String> config = new HashMap<>();

	public int password_length() {
		return Integer.valueOf(config.get("password_length"));
	}

	public Map<String, String> getConfig() {
		return config;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2Y, 10);
	}

}
