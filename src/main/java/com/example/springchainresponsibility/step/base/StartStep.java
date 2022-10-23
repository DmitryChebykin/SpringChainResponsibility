package com.example.springchainresponsibility.step.base;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.step.AbstractStep;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
@Order(-1)
public class StartStep extends AbstractStep {

    @Override
    public String getStepName() {
        return "start";
    }

    @Override
    protected Optional<Message> processAndApplyNext(Message message) {
        message.setResponseMap(new HashMap<>());
        return Optional.of(message);
    }
}
