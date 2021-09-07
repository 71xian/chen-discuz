package com.aoexe.discuz.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.aoexe.discuz.system.modular.user.entity.User;

/**
 * Redis配置类
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(User.class));
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new FastJson2JsonRedisSerializer<>(User.class));
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}

}
