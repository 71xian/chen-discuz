package com.aoexe.discuz.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.aoexe.discuz.system.core.cache.CategoryCache;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.modular.category.model.entity.Category;
import com.aoexe.discuz.system.modular.user.model.entity.User;

/**
 * Redis配置类
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
public class RedisConfig {
	
	@Bean
	public UserCache userCache(RedisConnectionFactory factory){
		return new UserCache(buildRedis(factory, User.class));
	}
	
	@Bean
	public CategoryCache categoryCache(RedisConnectionFactory factory) {
		return new CategoryCache(buildRedis(factory, Category.class));
	}
	
	private static <T> RedisTemplate<String, T> buildRedis(RedisConnectionFactory factory, Class<?> clazz) {
		RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(clazz));
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new FastJson2JsonRedisSerializer<>(clazz));
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
