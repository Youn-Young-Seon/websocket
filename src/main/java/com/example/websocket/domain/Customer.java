package com.example.websocket.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer implements CustomerInterface {
    private String name;
    private BigDecimal account;
    private List<Stock> haveStock = new ArrayList<>();
    private List<Stock> buyStock = new ArrayList<>();
    private List<Stock> sellStock = new ArrayList<>();

    @Override
    public void buy() {

    }

    @Override
    public void sell() {

    }

    @Override
    public List<Stock> getStocks() {
        return haveStock;
    }

    public Long investmentMoney() {
        return haveStock.stream().map(Stock::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).longValue();
    }
}
