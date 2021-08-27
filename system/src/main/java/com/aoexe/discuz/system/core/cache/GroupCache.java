package com.aoexe.discuz.system.core.cache;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.system.core.cache.base.AbstractRedisCacheOperator;
import com.aoexe.discuz.system.modular.group.entity.DzqGroup;

public class GroupCache extends AbstractRedisCacheOperator<DzqGroup>{

	public static final String GROUP_CACHE_PREFIX = "GROUP_";
	
	public GroupCache(RedisTemplate<String, DzqGroup> redisTemplate) {
		super(redisTemplate);
	}

	@Override
	public String getCommonKeyPrefix() {
		return GROUP_CACHE_PREFIX;
	}

}
