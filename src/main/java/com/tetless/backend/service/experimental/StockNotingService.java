package com.tetless.backend.service.experimental;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class StockNotingService {
	public Mono<Boolean> stockSellCount() {
		return Mono.just(true);
	}
}
