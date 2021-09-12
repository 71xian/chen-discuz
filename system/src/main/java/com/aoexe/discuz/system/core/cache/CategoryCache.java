package com.aoexe.discuz.system.core.cache;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.system.core.cache.abstractCache.AbstractCache;
import com.aoexe.discuz.system.modular.category.model.entity.Category;

public class CategoryCache extends AbstractCache<Category>{

	private static final String CATEGORY_PREFIX = "CATEGORY:";
	
	public CategoryCache(RedisTemplate<String, Category> redisTemplate) {
		super(redisTemplate);
	}

	public void set(Long key, Category value) {
		super.set(key.toString(), value);
	}
	
	public Category get(Long key) {
		return super.get(key.toString());
	}
	
	public void remove(Long key) {
		super.remove(key.toString());
	}
	
	@Override
	public String getCommonPrefix() {
		return CATEGORY_PREFIX;
	}

}
