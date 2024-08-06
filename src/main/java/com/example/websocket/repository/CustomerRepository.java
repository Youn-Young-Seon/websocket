package com.example.websocket.repository;

import com.example.websocket.domain.Customer;
import com.example.websocket.domain.Stock;
import com.example.websocket.enums.StockEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    @PostConstruct
    public void init() {
        customers.add(Customer.ofNameAndAccountAndStock("test", BigDecimal.valueOf(10_000_000), initStocks()));
        customers.add(Customer.ofNameAndAccountAndStock("asdf", BigDecimal.valueOf(10_000_000), initStocks()));
        customers.add(Customer.ofNameAndAccountAndStock("test2", BigDecimal.valueOf(10_000_000), initStocks()));
    }

    public Customer getCustomerByName(String name) {
        return customers.stream().filter(customer -> customer.getName().equals(name)).findFirst().orElse(null);
    }

    private List<Stock> initStocks() {
        return IntStream.range(0, 100)
                .mapToObj(i -> Stock.of(StockEnum.SAMSUNG, BigDecimal.ZERO))
                .toList();
    }
}
