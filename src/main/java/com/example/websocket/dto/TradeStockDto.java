package com.example.websocket.dto;

import com.example.websocket.domain.TradeStock;
import com.example.websocket.enums.TradeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class TradeStockDto {
    private TradeEnum tradeType;
    private List<StockDto> stocks = new ArrayList<>();
    private String customer;

    private TradeStockDto(TradeEnum tradeType, List<StockDto> stocks, String customer) {
        this.tradeType = tradeType;
        this.stocks = stocks;
        this.customer = customer;
    }

    public static TradeStockDto of(TradeStock tradeStock) {
        List<StockDto> toStockDtoList = tradeStock.getStocks().stream().map(StockDto::from).toList();
        return new TradeStockDto(tradeStock.getTradeType(), toStockDtoList, tradeStock.getCustomer().getName());
    }
}
