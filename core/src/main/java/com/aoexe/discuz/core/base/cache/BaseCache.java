package com.aoexe.discuz.core.base.cache;

import java.util.concurrent.TimeUnit;

public interface BaseCache<T> {

	void set(String key, T value);
	
	void set(String key, T value, Long ex, TimeUnit unit);
	
	T get(String key);
	
	boolean remove(String key);
	
	void hset(String key, String member, T value);
	
	T hget(String key, String member);
	
	long hremove(String key, String member);
	
	String getCommonPrefix();
}
