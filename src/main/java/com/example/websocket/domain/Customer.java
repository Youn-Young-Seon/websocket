package com.example.websocket.domain;

import com.example.websocket.enums.TradeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class Customer {
    private String name;
    private BigDecimal account;
    private List<Stock> haveStock = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, BigDecimal account, List<Stock> haveStock) {
        this(name);
        this.account = account;
        this.haveStock = haveStock;
    }

    public static Customer ofName(String name) {
        return new Customer(name);
    }

    public static Customer ofNameAndAccountAndStock(String name, BigDecimal account, List<Stock> haveStock) {
        return new Customer(name, account, haveStock);
    }

    public void tradeStocks(TradeStock tradeStock, List<TradeStock> tradeStockList) {
        if (tradeStockList.isEmpty()) {
            tradeStockList.add(tradeStock);
            return;
        }

        if (tradeStock.getTradeType().equals(TradeEnum.BUY)) {

        } else if(tradeStock.getTradeType().equals(TradeEnum.SELL)){

        }
    }

    public Long investmentMoney() {
        return haveStock.stream().map(Stock::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).longValue();
    }
}
