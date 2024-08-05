package com.example.websocket.domain;

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
public class Trade implements TradeInterface {
    private List<TradeStock> tradeStock = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition buyCondition = lock.newCondition();
    private Condition sellCondition = lock.newCondition();


    @Override
    public TradeStock executeTrade(TradeStock tradeStock) {
        log.info("{}", tradeStock);
        return null;
    }
}
