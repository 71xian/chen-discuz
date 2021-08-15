package cn.chenyuxian.discuz.core.base.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;

import cn.chenyuxian.discuz.core.common.CacheKey;

/**
 * redis缓存类
 *
 * @author chenyuxian
 * @date 2021-08-14
 */
public class DzqCache<T> {

	private static final boolean CACHE_SWITCH = true;
	
	private static final boolean CACHE_TTL = true;
	
	@Resource
	private final RedisTemplate<String, T> redisTemplate;
	
	public DzqCache(RedisTemplate<String, T> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	public void set(String key, T value, int ttl) {
		if(CACHE_TTL) {
			redisTemplate.boundValueOps(key).set(value, ttl, TimeUnit.SECONDS);
		}else {
			redisTemplate.boundValueOps(key).set(value);
		}
	}
	
	public T get(String key) {
		return redisTemplate.boundValueOps(key).get();
	}
	
	public void delKey(String key) {
		redisTemplate.delete(key);
	}
	
	public void delKeys(String...key) {
		List<String> keys = new ArrayList<>();
		Collections.addAll(keys, key);
		redisTemplate.delete(keys);
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
