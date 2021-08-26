package cn.chenyuxian.discuz.system.core.config;

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

import cn.chenyuxian.discuz.system.core.cache.GroupCache;
import cn.chenyuxian.discuz.system.modular.group.entity.DzqGroup;

/**
 * Redis配置类
 *
 * @author chenyuxian
 * @date 2021-08-20
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

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
		redisTemplate.setHashKeySerializer(new StringRedisSerializer()); 
		redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(DzqGroup.class));
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.afterPropertiesSet();
		return new GroupCache(redisTemplate);
	}
	
}
