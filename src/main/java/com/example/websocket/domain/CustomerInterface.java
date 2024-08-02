package com.example.websocket.domain;

import java.util.List;

public interface CustomerInterface {
    void buy();
    void sell();
    List<Stock> getStocks();
}
