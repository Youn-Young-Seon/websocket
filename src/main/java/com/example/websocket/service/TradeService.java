package com.example.websocket.service;

import com.example.websocket.domain.Customer;
import com.example.websocket.domain.Trade;
import com.example.websocket.domain.TradeStock;
import com.example.websocket.dto.TradeStockDto;
import com.example.websocket.repository.TradeRepository;
import org.springframework.stereotype.Service;

@Service
public class TradeService {
    private final Trade trade;
    private final TradeRepository tradeRepository;

    public TradeService(Trade trade, TradeRepository tradeRepository) {
        this.trade = trade;
        this.tradeRepository = tradeRepository;
    }

    public void getTrade(TradeStockDto tradeStockDto) {
        Customer customer = tradeRepository.getCustomerByName(tradeStockDto.getCustomer());
        TradeStock tradeStock = TradeStock.toTradeStock(tradeStockDto, customer);
        trade.executeTrade(tradeStock);
    }
}
