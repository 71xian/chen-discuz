package com.aoexe.discuz.system.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.aoexe.discuz.system.modular.user.entity.User;

/**
 * 缓存当前登录用户的信息
 *
 * @author chenyuxian
 * @date 2021-08-25
 */
@Component
public class UserCache {

	/**
	 * 登录用户缓存前缀
	 */
	public static final String LOGIN_USER_CACHE_PREFIX = "LOGIN_USER_";

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	public String getCommonKey() {
		return LOGIN_USER_CACHE_PREFIX;
	}

	public void set(String id, String uuid, User value) {
		redisTemplate.boundHashOps(getCommonKey() + id).put(uuid, value);
	}

	public User get(String id, String uuid) {
		return (User) redisTemplate.boundHashOps(getCommonKey() + id).get(uuid);
	}

	public Long remove(String id, String uuid) {
		return redisTemplate.boundHashOps(getCommonKey() + id).delete(uuid);
	}

}
