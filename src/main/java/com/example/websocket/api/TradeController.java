package com.example.websocket.api;

import com.example.websocket.dto.TradeStockDto;
import com.example.websocket.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @MessageMapping("/trade")
    @SendTo("/topic/trade")
    public ResponseEntity<TradeStockDto> tradeStock(TradeStockDto tradeStockDto) {
        tradeService.getTrade(tradeStockDto);
        return ResponseEntity.ok(tradeStockDto);
    }
}
