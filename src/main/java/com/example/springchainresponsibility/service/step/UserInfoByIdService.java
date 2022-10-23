package com.example.springchainresponsibility.service.step;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.payload.Response;
import com.example.springchainresponsibility.service.event.UserIdProcessEvent;
import com.example.springchainresponsibility.step.AbstractStep;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Order(1)
public class UserInfoByIdService extends AbstractStep {
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserInfoByIdService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    protected Optional<Message> processAndApplyNext(Message message) {
        String userId = message.getRequestMap().get("userId");

        Response response;

        if (userId.equals("086-40-4550")) {
            applicationEventPublisher.publishEvent(new UserIdProcessEvent(userId));
            response = Response.builder().answers(List.of("UserInfo : Cheryle Waelchi")).build();
            message.getResponseMap().put("userInfoById", response);

            return Optional.of(message);
        }

        if (userId.equals("000-00-000")) {
            throw new IllegalArgumentException("Unknown service error processing userId ");
        }

        response = Response.builder().answers(List.of("Ticket : UserId not found")).build();
        message.getResponseMap().put("userInfoById", response);

        return Optional.of(message);
    }

    @Override
    public String getStepName() {
        return "userInfoById";
    }
}
