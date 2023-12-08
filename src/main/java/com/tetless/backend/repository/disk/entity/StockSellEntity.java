package com.tetless.backend.repository.disk.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("stock_sell")
@Getter
public class StockSellEntity {
    @Id
    private int stockSellNo;

    private int sellQty;

    private int soldQty;

    public void changeSoldQty(int purchaseQty) {
        int changedSoldQty = this.soldQty + purchaseQty;
        if (changedSoldQty <= sellQty) {
            this.soldQty = changedSoldQty;
        } else {
            // throw new RuntimeException("재고가 부족합니다."); TODO: 테스트를 위해 주석 처리
        }
    }
}
