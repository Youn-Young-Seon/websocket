package com.example.websocket.domain;

import com.example.websocket.dto.StockDto;
import com.example.websocket.enums.StockEnum;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Stock {
    private StockEnum stockType;
    private BigDecimal price;

    private Stock(StockEnum stockType, BigDecimal price) {
        this.stockType = stockType;
        this.price = price;
    }

    public static Stock of(StockEnum stockType, BigDecimal price) {
        return new Stock(stockType, price);
    }

    public static Stock toStock(StockDto stockDto) {
        return new Stock(stockDto.getStockType(), stockDto.getPrice());
    }
}
