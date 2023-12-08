package com.tetless.backend.controller;

import com.tetless.backend.repository.inmemory.StockMemoryRepository;
import com.tetless.backend.service.disk.StockDiskService;
import com.tetless.backend.service.experimental.StockNotingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockMemoryRepository stockRepository;
    private final StockDiskService stockDiskService;
    private final StockNotingService stockNotingService;

    @GetMapping("/inmemory/stock/sell-count")
    public Flux<Boolean> stockSellCountByInMemory() {
        return stockRepository.stockSellCount();
    }

    @GetMapping("/disk/stock/sell-count")
    public Mono<Boolean> stockSellCountByDisk() throws InterruptedException {
        return stockDiskService.stockSellCountWithStockBuy();
    }

    @GetMapping("/experimental/stock/sell-count")
    public Mono<Boolean> stockSellCountByNoting() {
        return stockNotingService.stockSellCount();
    }
}
