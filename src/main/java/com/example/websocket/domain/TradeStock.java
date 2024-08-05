package com.example.websocket.domain;

import com.example.websocket.dto.StockDto;
import com.example.websocket.dto.TradeStockDto;
import com.example.websocket.enums.TradeEnum;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class TradeStock {
    private TradeEnum tradeType;
    private final List<Stock> stocks = new ArrayList<>();
    private Customer customer;

    private TradeStock(TradeEnum tradeType, List<StockDto> stockDtos, Customer customer) {
        this.tradeType = tradeType;
        for (StockDto stockDto : stockDtos) {
            this.stocks.add(Stock.toStock(stockDto));
        }
        this.customer = customer;
    }

    public static TradeStock toTradeStock(TradeStockDto tradeStockDto, Customer customer) {
        return new TradeStock(tradeStockDto.getTradeType(), tradeStockDto.getStocks(), customer);
    }
}
