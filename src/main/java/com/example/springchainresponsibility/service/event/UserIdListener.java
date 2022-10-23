package com.example.springchainresponsibility.service.event;

import com.example.springchainresponsibility.service.DataRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserIdListener {
    private final DataRepository dataRepository;

    public UserIdListener(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @EventListener
    public void saveUserId(UserIdProcessEvent userIdProcessEvent) {
        dataRepository.saveUserId(userIdProcessEvent.getUserId());
    }
}
