package com.example.websocket.repository;

import com.example.websocket.domain.Stock;
import com.example.websocket.enums.StockEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StockRepository {
    private List<Stock> samsungStocks = new ArrayList<>();
    private List<Stock> LGStocks = new ArrayList<>();
    private List<Stock> naverStocks = new ArrayList<>();
    private List<Stock> kakaoStocks = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            samsungStocks.add(Stock.of(StockEnum.SAMSUNG, BigDecimal.valueOf(72_000)));
        }
        for (int i = 0; i < 100; i++) {
            LGStocks.add(Stock.of(StockEnum.LG, BigDecimal.valueOf(79_200)));
        }
        for (int i = 0; i < 100; i++) {
            naverStocks.add(Stock.of(StockEnum.NAVER, BigDecimal.valueOf(159_800)));
        }
        for (int i = 0; i < 100; i++) {
            kakaoStocks.add(Stock.of(StockEnum.KAKAO, BigDecimal.valueOf(37_150)));
        }
    }

    public List<Stock> getStocksByType(StockEnum stockEnum) {
        switch (stockEnum) {
            case SAMSUNG -> { return samsungStocks; }
            case LG -> { return LGStocks; }
            case NAVER -> { return naverStocks; }
            case KAKAO -> { return kakaoStocks; }
            default -> throw new IllegalStateException("Unexpected value: " + stockEnum);
        }
    }
}
