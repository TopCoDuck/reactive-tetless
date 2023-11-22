package com.tetless.backend.repository.disk;

import com.tetless.backend.repository.disk.entity.StockSellEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StockSellDiskRepository extends ReactiveCrudRepository<StockSellEntity, Integer> {
}
