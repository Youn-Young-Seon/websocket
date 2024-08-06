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
    private List<Stock> stocks = new ArrayList<>();
    private Customer customer;

    private TradeStock(TradeEnum tradeType, List<StockDto> stockDtos, String customer) {
        this.tradeType = tradeType;
        for (StockDto stockDto : stockDtos) {
            this.stocks.add(Stock.toStock(stockDto));
        }
        this.customer = new Customer(customer);
    }

    private TradeStock(TradeEnum tradeType, List<Stock> stocks, Customer customer) {
        this.tradeType = tradeType;
        this.stocks = stocks;
        this.customer = customer;
    }

    public static TradeStock of(TradeEnum tradeType, List<Stock> stocks, Customer customer) {
        return new TradeStock(tradeType, stocks, customer);
    }

    public static TradeStock toTradeStock(TradeStockDto tradeStockDto) {
        return new TradeStock(tradeStockDto.getTradeType(), tradeStockDto.getStocks(), tradeStockDto.getCustomer());
    }
}
