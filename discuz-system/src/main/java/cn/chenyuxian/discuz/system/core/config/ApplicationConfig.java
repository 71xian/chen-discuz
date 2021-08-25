package cn.chenyuxian.discuz.system.core.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * 系统配置容器
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Component
public class ApplicationConfig {

	@Getter
	private static final Map<String, String> settings = new HashMap<>();
	
	public static int password_length() {
		return Integer.valueOf(settings.get("password_length"));
	}
	
}
