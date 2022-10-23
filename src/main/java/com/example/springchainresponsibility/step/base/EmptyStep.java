package com.example.springchainresponsibility.step.base;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.step.ProcessStep;

public class EmptyStep implements ProcessStep {
    @Override
    public void setNext(ProcessStep nextStep) {

    }

    @Override
    public Message process(Message message) {
        return message;
    }

    @Override
    public void setErrorStep(ProcessStep errorStep) {

    }

    @Override
    public String getStepName() {
        return "emptyStep";
    }
}
