package com.aoexe.discuz.system.core.cache;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.core.context.login.LoginUser;
import com.aoexe.discuz.system.core.cache.base.AbstractRedisCacheOperator;

/**
 * 缓存当前登录用户的信息
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
public class UserCache extends AbstractRedisCacheOperator<LoginUser>{

	/**
	 * 登录用户缓存前缀
	 */
	public static final String LOGIN_USER_CACHE_PREFIX = "LOGIN_USER_";
	
	public UserCache(RedisTemplate<String, LoginUser> redisTemplate) {
		super(redisTemplate);
	}

	@Override
	public String getCommonKeyPrefix() {
		return LOGIN_USER_CACHE_PREFIX;
	}
	
	@Override
	public void put(String key, LoginUser value) {
		super.put(key, value, 2 * 60 * 60L);
	}
	
}
