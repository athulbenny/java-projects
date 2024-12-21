package com.credai.multiapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.credai.multiapi.model.UserDtoFromApi;

@Configuration
public class RedisConfig {
	
	@Bean
	RedisConnectionFactory redisConnectionFactory() {
	    LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory("192.168.231.43", 6379);
	 	    return lettuceConnectionFactory;
	}

	
	@Bean
	RedisTemplate<String, UserDtoFromApi> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, UserDtoFromApi> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());

        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserDtoFromApi.class));//JdkSerializationRedisSerializer()

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(UserDtoFromApi.class));//JdkSerializationRedisSerializer()

        return template;
    }
}
