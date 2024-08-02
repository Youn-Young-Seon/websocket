package com.example.websocket.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Stock {
    private String name;
    private BigDecimal price;
}
