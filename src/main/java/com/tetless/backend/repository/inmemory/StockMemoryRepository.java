package com.tetless.backend.repository.inmemory;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class StockMemoryRepository {

	private final ReactiveRedisTemplate<String, String> redisTemplate;
	
	private static final String STOCK_SELL_KEY = "stock:sell";
	
	
	public Flux<Boolean> stockSellCount() {
		return redisTemplate.execute(script(), List.of(STOCK_SELL_KEY), List.of());
	}

	@Bean
	RedisScript<Boolean> script() {
		  return RedisScript.of(luaScript, Boolean.class);
	}
	
	String luaScript = "local limit = redis.call('HGET', KEYS[1], 'limit')\r\n"
			+"local sold = redis.call('HGET', KEYS[1], 'sold')\r\n"
			+ "if limit == false\r\n"
			+ "  then return true\r\n"
			+ "end\r\n"
			+ "if tonumber(sold) < tonumber(limit) \r\n"
			+ "  then redis.call('HINCRBY', KEYS[1], 'sold', 1)\r\n"
			+ "  return true\r\n"
			+ "end\r\n"
			+ "return false";
}
