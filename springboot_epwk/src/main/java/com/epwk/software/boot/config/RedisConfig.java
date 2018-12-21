package com.epwk.software.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.epwk.software.boot.utils.RedisConverter;

/**
 *redis{对象序列初始化} 
 * @author zhanmeihe
 *
 *
 */
@Configuration
public class RedisConfig {

	/**
	 * 
	 * @param redisConnectionFactory
	 * @return 自定义redisTemplate，自带的bean没有序列化器
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());// 设置key的序列化器
		redisTemplate.setValueSerializer(new RedisConverter());// 设置值的序列化器
		return redisTemplate;
	}

}