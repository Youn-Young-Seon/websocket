package com.example.websocket.domain;

import com.example.websocket.dto.StockDto;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Stock {
    private String name;
    private BigDecimal price;

    private Stock(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public static Stock of(String name, BigDecimal price) {
        return new Stock(name, price);
    }

    public static Stock toStock(StockDto stockDto) {
        return new Stock(stockDto.getName(), stockDto.getPrice());
    }
}
