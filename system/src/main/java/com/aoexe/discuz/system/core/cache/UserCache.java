package com.aoexe.discuz.system.core.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.aoexe.discuz.system.core.cache.abstractCache.AbstractCache;
import com.aoexe.discuz.system.modular.user.model.entity.User;

/**
 * 缓存当前登录用户的信息
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
public class UserCache extends AbstractCache<User> {

	private static final String LOGIN_USER_PREFIX = "LOGIN_USER:";

	public UserCache(RedisTemplate<String, User> redisTemplate) {
		super(redisTemplate);
	}

	@Override
	public void set(String key, User value) {
		super.set(key, value, 2L, TimeUnit.HOURS);
	}

	@Override
	public String getCommonPrefix() {
		return LOGIN_USER_PREFIX;
	}

}
