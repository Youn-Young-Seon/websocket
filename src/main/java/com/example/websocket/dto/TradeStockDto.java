package com.example.websocket.dto;

import com.example.websocket.domain.Stock;
import com.example.websocket.enums.TradeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
public class TradeStockDto {
    private TradeEnum tradeType;
    private List<StockDto> stocks = new ArrayList<>();
    private String customer;
}
