package com.example.springchainresponsibility.step;

import com.example.springchainresponsibility.payload.Message;

public interface ProcessStep extends ChainElement<ProcessStep> {

    Message process(Message message);

    void setErrorStep(ProcessStep errorStep);

    String getStepName();
}
