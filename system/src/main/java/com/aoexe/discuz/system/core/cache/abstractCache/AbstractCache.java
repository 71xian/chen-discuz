package com.aoexe.discuz.system.core.cache.abstractCache;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.core.base.cache.BaseCache;

public abstract class AbstractCache<T> implements BaseCache<T> {
	
	protected RedisTemplate<String, T> redisTemplate; 
	
	public AbstractCache(RedisTemplate<String, T> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public void set(String key, T value) {
		redisTemplate.boundValueOps(getCommonPrefix() + key).set(value);
	}
	
	@Override
	public void set(String key, T value, Long ex, TimeUnit unit) {
		redisTemplate.boundValueOps(getCommonPrefix() + key).set(value, ex, unit);
	}
	
	@Override
	public T get(String key) {
		return redisTemplate.boundValueOps(getCommonPrefix() + key).get();
	}
	
	@Override
	public boolean remove(String key) {
		return redisTemplate.delete(getCommonPrefix() + key);
	}
	
	@Override
	public void hset(String key, String member, T value) {
		redisTemplate.boundHashOps(getCommonPrefix() + key).put(member, value);
	}
	
	@Override
	public T hget(String key, String member) {
		return (T) redisTemplate.boundHashOps(getCommonPrefix() + key).get(member);
	}
	
	@Override
	public long hremove(String key, String member) {
		return redisTemplate.boundHashOps(getCommonPrefix() + key).delete(member);
	}
	
}
