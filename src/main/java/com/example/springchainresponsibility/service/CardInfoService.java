package com.example.springchainresponsibility.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CardInfoService {
    public BigDecimal getBalance(String cardId) {
        if (cardId.equals("1234-2121-1221-1211")) {
            return BigDecimal.valueOf(50000);
        }

        return BigDecimal.valueOf(1000);
    }
}
