package com.example.websocket.service;

import com.example.websocket.domain.Trade;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    private final Trade trade;

    public TradeService(Trade trade) {
        this.trade = trade;
    }
}
