package com.example.websocket.domain;

import com.example.websocket.enums.StockEnum;
import com.example.websocket.enums.TradeEnum;
import com.example.websocket.repository.StockRepository;
import com.example.websocket.service.CustomerService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Slf4j
@Component
public class Trade {
    private ReentrantLock lock = new ReentrantLock();
    private Condition buyCondition = lock.newCondition();
    private Condition sellCondition = lock.newCondition();
    private List<TradeStock> tradeStockList = new ArrayList<>();

    private final StockRepository stockRepository;
    private final CustomerService customerService;

    public Trade(StockRepository stockRepository, CustomerService customerService) {
        this.stockRepository = stockRepository;
        this.customerService = customerService;
    }

    public List<TradeStock> executeTrade(TradeStock tradeStock) {
        try {
            lock.lock();

            TradeEnum tradeType = tradeStock.getTradeType();
            List<Stock> tradeStocks = tradeStock.getStocks();
            Customer customer = customerService.getCustomerInfo(tradeStock.getCustomer().getName());

            TradeStock addTradeStock = TradeStock.of(tradeType, tradeStocks, customer);

            customer.tradeStocks(addTradeStock, tradeStockList);

            return tradeStockList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
