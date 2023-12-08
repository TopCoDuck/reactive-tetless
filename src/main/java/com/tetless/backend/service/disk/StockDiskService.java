package com.tetless.backend.service.disk;

import com.tetless.backend.model.disk.BuyStatus;
import com.tetless.backend.repository.disk.StockBuyDiskRepository;
import com.tetless.backend.repository.disk.StockSellDiskRepository;
import com.tetless.backend.repository.disk.entity.StockBuyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockDiskService {
    private final StockSellDiskRepository stockSellDiskRepository;
    private final StockBuyDiskRepository stockBuyDiskRepository;

    //@Transactional
    public Mono<Boolean> stockSellCount() {
        //StockSellEntity stockSell = stockDiskRepository.findByStockSellNo(1);
        //stockSell.changeSoldQty(1);
        //stockDiskRepository.save(stockSell);
        return Mono.just(null);
    }

    public Mono<Boolean> stockSellCountWithStockBuy() throws InterruptedException {

        return Mono.zip(stockSellDiskRepository.findById(1),
                        stockBuyDiskRepository.sumBuyQuantity(1))
                .flatMap(item -> {
                    val stockSell = item.getT1();
                    val preSumBuyQuantity = item.getT2();

                    if (preSumBuyQuantity < stockSell.getSellQty()) {
                        val stockBuy = StockBuyEntity.create(1, 1);

                        return Mono.just(stockBuy)
                                .flatMap(stockBuyDiskRepository::save)
                                .then(stockBuyDiskRepository.sumBuyQuantity(1))
                                .map(postSumBuyQutity -> {
                                    if (postSumBuyQutity > stockSell.getSellQty()) {
                                        stockBuy.changeStatus(BuyStatus.FAIL);
                                    } else {
                                        stockBuy.changeStatus(BuyStatus.DONE);
                                    }
                                    return stockBuy;
                                })
                                .flatMap(stockBuyDiskRepository::save)
                                .thenReturn(true);
                    } else {
                        return Mono.just(false);
                    }
                });
    }
}
