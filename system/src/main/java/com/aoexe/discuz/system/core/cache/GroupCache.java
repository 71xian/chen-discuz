package com.aoexe.discuz.system.core.cache;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.system.core.cache.base.AbstractRedisCacheOperator;
import com.aoexe.discuz.system.modular.group.entity.DzqGroup;

/**
 * 缓存用户组信息
 *
 * @author chenyuxian
 * @date 2021-08-30
 */
public class GroupCache extends AbstractRedisCacheOperator<DzqGroup> {

	private static final String GROUP_CACHE_PREFIX = "GROUP_";

	public GroupCache(RedisTemplate<String, DzqGroup> redisTemplate) {
		super(redisTemplate);
	}

	@Override
	public String getCommonKeyPrefix() {
		return GROUP_CACHE_PREFIX;
	}
}
