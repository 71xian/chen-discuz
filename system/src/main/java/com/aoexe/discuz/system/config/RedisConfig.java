package com.aoexe.discuz.system.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.aoexe.discuz.core.context.login.LoginUser;
import com.aoexe.discuz.system.core.cache.GroupCache;
import com.aoexe.discuz.system.core.cache.UserCache;
import com.aoexe.discuz.system.modular.group.entity.DzqGroup;
import com.aoexe.discuz.system.modular.user.entity.User;

/**
 * Redis配置类
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	private Duration duration = Duration.ofHours(12);
	
	@Autowired
	private LettuceConnectionFactory factory;

	@Bean
	public RedisCacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(this.duration)
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(Object.class)))
				.disableCachingNullValues();
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(configuration)
				.transactionAware()
				.build();
	}
	
	@Bean
	public GroupCache groupCache() {
		RedisTemplate<String, DzqGroup> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer()); 
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(DzqGroup.class)); 
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.afterPropertiesSet();
		return new GroupCache(redisTemplate);
	}
	
	@Bean
	public UserCache userCache() {
		RedisTemplate<String, LoginUser> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(User.class)); 
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.afterPropertiesSet();
		return new UserCache(redisTemplate);
	}
	
}
