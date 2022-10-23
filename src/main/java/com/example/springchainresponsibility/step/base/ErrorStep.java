package com.example.springchainresponsibility.step.base;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.step.AbstractStep;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ErrorStep extends AbstractStep {
    ErrorStep() {
        super.setNext(new EmptyStep());
    }

    @Override
    protected Optional<Message> processAndApplyNext(Message message) {
        return Optional.of(message);
    }

    @Override
    public String getStepName() {
        return "error";
    }
}
