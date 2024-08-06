package com.example.websocket.service;

import com.example.websocket.domain.Stock;
import com.example.websocket.domain.Trade;
import com.example.websocket.domain.TradeStock;
import com.example.websocket.dto.TradeStockDto;
import com.example.websocket.enums.StockEnum;
import com.example.websocket.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {
    private final Trade trade;
    private final StockRepository stockRepository;
    private final CustomerService customerService;

    public TradeService(Trade trade, StockRepository stockRepository, CustomerService customerService) {
        this.trade = trade;
        this.stockRepository = stockRepository;
        this.customerService = customerService;
    }

    public List<TradeStockDto> getTrade(TradeStockDto tradeStockDto) {
        TradeStock tradeStock = TradeStock.toTradeStock(tradeStockDto);
        List<TradeStock> tradeStocks = trade.executeTrade(tradeStock);
        return tradeStocks.stream().map(TradeStockDto::of).toList();
    }

    public List<Stock> getStocks(StockEnum stockEnum) {
        return stockRepository.getStocksByType(stockEnum);
    }
}
