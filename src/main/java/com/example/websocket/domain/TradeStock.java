package com.example.websocket.domain;

import com.example.websocket.enums.TradeEnum;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TradeStock {
    private TradeEnum tradeType;
    private final List<Stock> stocks = new ArrayList<>();
    private Customer customer;
}
