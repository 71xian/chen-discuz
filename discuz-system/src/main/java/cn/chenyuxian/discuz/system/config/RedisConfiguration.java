package cn.chenyuxian.discuz.system.config;

import java.time.Duration;

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
	public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer()); 
		// value序列化
		redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class)); 
		// Hash key序列化
		redisTemplate.setHashKeySerializer(new StringRedisSerializer()); 
		// Hash value序列化
		redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}
}