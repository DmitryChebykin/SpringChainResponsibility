package com.example.springchainresponsibility.service;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.step.ChainElement;
import com.example.springchainresponsibility.step.ProcessStep;
import com.example.springchainresponsibility.step.base.EmptyStep;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessFacade {
    private final ProcessStep startStep;

    public ProcessFacade(List<ProcessStep> stepList) {
        List<ProcessStep> steps = stepList.stream()
                .filter(processStep -> !processStep.getStepName().contains("error")).toList();

        this.startStep = ChainElement.buildChain(steps, new EmptyStep());
    }

    public Message startChain(Message message) {
        return startStep.process(message);
    }
}
