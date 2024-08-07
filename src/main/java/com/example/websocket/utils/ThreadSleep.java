package com.example.websocket.utils;

public class ThreadSleep {
    public static void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
