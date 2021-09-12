package com.aoexe.discuz.core.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 */
@Component
public class RedisUtil {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	public void set(String key, String value, Long timeout, TimeUnit unit) {
		redisTemplate.boundValueOps(key).set(value, timeout, unit);
	}
	
	public void set(String key, String value) {
		redisTemplate.boundValueOps(key).set(value);
	}
	
	public String get(String key) {
		return redisTemplate.boundValueOps(key).get();
	}
	
	public void leftPush(String key, Collection<String> values) {
		String[] vs = new String[values.size()];
		Collections.addAll(values, vs);
		redisTemplate.boundListOps(key).leftPushAll(vs);
	}
	
	public List<String> leftAll(String key){
		return redisTemplate.boundListOps(key).range(0, -1);
	}
	
	public boolean remove(String key) {
		return redisTemplate.delete(key);
	}
}
