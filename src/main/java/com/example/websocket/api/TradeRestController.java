package com.example.websocket.api;

import com.example.websocket.domain.Trade;
import com.example.websocket.service.TradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeRestController {
    private final TradeService tradeService;

    public TradeRestController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @MessageMapping("/trade")
    @SendTo("/topic/trade")
    public ResponseEntity<Trade> tradeStock() {
        Trade trade = new Trade();
        return ResponseEntity.ok(trade);
    }
}
