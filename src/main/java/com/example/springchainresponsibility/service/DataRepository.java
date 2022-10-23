package com.example.springchainresponsibility.service;

import org.springframework.stereotype.Component;

@Component
public class DataRepository {
    public void saveUserId(String userId) {
        System.out.println("UserId saved in database");
    }
}
