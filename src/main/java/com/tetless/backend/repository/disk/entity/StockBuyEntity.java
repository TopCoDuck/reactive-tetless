package com.tetless.backend.repository.disk.entity;

import com.tetless.backend.model.disk.BuyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("stock_buy")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class StockBuyEntity {
    @Id
    private int stockBuyNo;

    private int stockSellNo;

    private int buyQty;

    private BuyStatus buyStatus;

    public static StockBuyEntity create(int stockSellNo, int buyQty) {
        return StockBuyEntity.builder()
                .stockSellNo(stockSellNo)
                .buyQty(buyQty)
                .buyStatus(BuyStatus.PREOCCUPY)
                .build();
    }

    public void changeStatus(BuyStatus buyStatus) {
        this.buyStatus = buyStatus;
    }
}
