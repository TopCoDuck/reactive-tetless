package com.tetless.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    ReactiveRedisTemplate<String, String> redisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext<String, String> serializationContext =
                RedisSerializationContext.<String, String>newSerializationContext(new StringRedisSerializer())
                        .value(new StringRedisSerializer())
                        .build();

        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    @Bean
    public RedisScript<Boolean> stockSellScript() {
        Resource scriptSource = new ClassPathResource("scripts/stock-sell.lua");
        return RedisScript.of(scriptSource, Boolean.class);
    }
}
