package com.aoexe.discuz.system.core.cache;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.system.core.cache.base.AbstractRedisCacheOperator;

/**
 * 用于保存一些不常修改的东西
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
public class MappingCache extends AbstractRedisCacheOperator<Map<String, Object>>{

	public static final String TRANSLATES_CACHE_PREFIX = "MAPPINGS_";
	
	public MappingCache(RedisTemplate<String, Map<String, Object>> redisTemplate) {
		super(redisTemplate);
	}

	@Override
	public String getCommonKeyPrefix() {
		return TRANSLATES_CACHE_PREFIX;
	}

}
