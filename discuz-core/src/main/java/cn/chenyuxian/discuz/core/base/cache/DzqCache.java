package cn.chenyuxian.discuz.core.base.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import cn.chenyuxian.discuz.core.common.CacheKey;

/**
 * redis缓存类
 *
 * @author chenyuxian
 * @date 2021-08-14
 */
@Component
public class DzqCache<T> {

	private static final boolean CACHE_SWITCH = true;
	
	private static final boolean CACHE_TTL = true;
	
	private final RedisTemplate<String, T> redisTemplate;
	
	@Autowired
	public DzqCache(RedisTemplate<String, T> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 设置键值对，若开启过期时间则设置过期时间
	 *
	 * @param key
	 * @param value
	 * @param ttl
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public void set(String key, T value, int ttl) {
		if(CACHE_TTL) {
			redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
		}else {
			redisTemplate.opsForValue().set(key, value);
		}
	}
	
	/**
	 * 
	 *
	 * @param key
	 * @return
	 * @author chenyuxian
	 * @date 2021-08-15
	 */
	public T get(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public void delKey(String key) {
		redisTemplate.delete(key);
	}
	
	public void delKeys(String...key) {
		List<String> keys = new ArrayList<>();
		Collections.addAll(keys, key);
		redisTemplate.delete(keys);
	}
	
	public void delHashKey(String key, String... hashKey) {
		redisTemplate.opsForHash().delete(key, hashKey);
	}
	
	public void hSet(String key, String hashKey, T value) {
		redisTemplate.boundHashOps(key).put(hashKey, value);
	}
	
	public boolean exists(String key) {
		if(CACHE_SWITCH) {
			
		}
		return true;
	}
	
	public void delListCache() {
		String[] keys = {
				CacheKey.CATEGORIES,
				CacheKey.LIST_THREADS_V3_CREATE_TIME,
				CacheKey.LIST_THREADS_V3_SEQUENCE,
				CacheKey.LIST_THREADS_V3_VIEW_COUNT,
				CacheKey.LIST_THREADS_V3_POST_TIME,
				CacheKey.LIST_THREADS_V3_COMPLEX,
				CacheKey.LIST_THREADS_V3_ATTENTION
		};
		delKeys(keys);
	}
}
