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
	private final RedisScript<Boolean> stockSellScript;
	private static final String STOCK_SELL_KEY = "stock:sell";

	public Flux<Boolean> stockSellCount() {
		return redisTemplate.execute(stockSellScript, List.of(STOCK_SELL_KEY), List.of());
	}
}
