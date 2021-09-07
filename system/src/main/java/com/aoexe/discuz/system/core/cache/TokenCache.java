package com.aoexe.discuz.system.core.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Token缓存
 *
 * @author chenyuxian
 * @date 2021-09-06 15:37:04
 */
@Component
public class TokenCache {

	private static final String TOKEN_KEY = "TOKEN_";

	@Autowired
	private StringRedisTemplate redisTemplate;

	public void set(String key, String token) {
		redisTemplate.boundValueOps(getCommonKey() + key).set(token, 7, TimeUnit.DAYS);
	}

	public String get(String key) {
		return redisTemplate.boundValueOps(getCommonKey() + key).get();
	}
	
	public boolean remove(String key) {
		return redisTemplate.delete(getCommonKey() + key);
	}

	public String getCommonKey() {
		return TOKEN_KEY;
	}

}
