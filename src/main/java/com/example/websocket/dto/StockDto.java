package com.example.websocket.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockDto {
    private String name;
    private BigDecimal price;
}
