package com.example.springchainresponsibility.service.event;

import com.example.springchainresponsibility.service.DataRepository;
import org.springframework.context.event.EventListener;

public class UserIdListener {
    private final DataRepository dataRepository;

    public UserIdListener(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @EventListener
    void saveUserId(UserIdProcessEvent userIdProcessEvent) {
        dataRepository.saveUserId(userIdProcessEvent.getUserId());
    }
}
