package com.aoexe.discuz.system.core.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.system.core.cache.abstractCache.AbstractCache;
import com.aoexe.discuz.system.modular.setting.model.entity.Settings;

/**
 * 缓存配置信息
 *
 * @author chenyuxian
 * @date 2021-10-01 15:17:23
 */
public class SettingsCache extends AbstractCache<Settings> {

	private static final String SETTING_PREFIX = "SETTINGS:";

	public SettingsCache(RedisTemplate<String, Settings> redisTemplate) {
		super(redisTemplate);
	}

	@Override
	public void set(String key, Settings value) {
		super.set(key, value, 1L, TimeUnit.HOURS);
	}

	@Override
	public String getCommonPrefix() {
		return SETTING_PREFIX;
	}

}
