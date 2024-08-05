package com.example.websocket.repository;

import com.example.websocket.domain.Customer;
import com.example.websocket.domain.Stock;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TradeRepository {
    private List<Customer> customers = new ArrayList<>();
    private List<Stock> samsungStocks = new ArrayList<>();
    private List<Stock> LGStocks = new ArrayList<>();
    private List<Stock> naverStocks = new ArrayList<>();
    private List<Stock> kakaoStocks = new ArrayList<>();

    @PostConstruct
    public void init() {
        customers.add(Customer.ofNameAndAccount("test", BigDecimal.valueOf(30_000_000)));
        customers.add(Customer.ofNameAndAccount("asdf", BigDecimal.valueOf(30_000_000)));
        for (int i = 0; i < 100; i++) {
            samsungStocks.add(Stock.of("Samsung", BigDecimal.valueOf(72_000)));
        }
        for (int i = 0; i < 100; i++) {
            LGStocks.add(Stock.of("LG", BigDecimal.valueOf(79_200)));
        }
        for (int i = 0; i < 100; i++) {
            naverStocks.add(Stock.of("Naver", BigDecimal.valueOf(159_800)));
        }
        for (int i = 0; i < 100; i++) {
            kakaoStocks.add(Stock.of("Kakao", BigDecimal.valueOf(37_150)));
        }
    }

    public Customer getCustomerByName(String name) {
        return customers.stream().filter(customer -> customer.getName().equals(name)).findFirst().orElse(null);
    }
}
