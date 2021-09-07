package com.aoexe.discuz.system.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.aoexe.discuz.system.modular.config.entity.Config;

@Component
public class ConfigCache {

	private static final Map<String, Config> CONFIG_CACHE = new ConcurrentHashMap<>();
	
	public void set(Config config) {
		CONFIG_CACHE.put(config.getConfigKey(), config);
	}
	
	public Config get(String key) {
		return CONFIG_CACHE.get(key);
	}
	
	public void remove(String key) {
		CONFIG_CACHE.remove(key);
	}
	
	public String getSecret() {
		return CONFIG_CACHE.get("site_secret").getConfigValue();
	}
}
