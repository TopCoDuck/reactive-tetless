package com.tetless.backend.repository.disk;

import com.tetless.backend.repository.disk.entity.StockBuyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StockBuyDiskRepository extends ReactiveCrudRepository<StockBuyEntity, Integer> {
    @Query("SELECT coalesce(sum(buy_qty),0) FROM stock_buy WHERE stock_sell_no = :stockSellNo AND buy_status IN ('PREOCCUPY','DONE')")
    Mono<Integer> sumBuyQuantity(@Param("stockSellNo") Integer stockSellNo);
}
