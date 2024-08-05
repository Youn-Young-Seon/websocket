package com.example.websocket.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class Customer implements CustomerInterface {
    private String name;
    private BigDecimal account;
    private List<Stock> haveStock = new ArrayList<>();
    private List<Stock> buyStock = new ArrayList<>();
    private List<Stock> sellStock = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, BigDecimal account) {
        this(name);
        this.account = account;
    }

    public static Customer ofName(String name) {
        return new Customer(name);
    }

    public static Customer ofNameAndAccount(String name, BigDecimal account) {
        return new Customer(name, account);
    }

    @Override
    public void buy() {

    }

    @Override
    public void sell() {

    }

    public Long investmentMoney() {
        return haveStock.stream().map(Stock::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).longValue();
    }
}
