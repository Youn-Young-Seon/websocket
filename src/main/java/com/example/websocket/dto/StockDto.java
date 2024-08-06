package com.example.websocket.dto;

import com.example.websocket.domain.Stock;
import com.example.websocket.enums.StockEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class StockDto {
    private StockEnum stockType;
    private BigDecimal price;

    public static StockDto from(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setStockType(stock.getStockType());
        stockDto.setPrice(stock.getPrice());
        return stockDto;
    }
}
