package com.example.springchainresponsibility.service.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserIdProcessEvent {
    private String userId;
}
