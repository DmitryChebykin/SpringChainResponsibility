package com.example.springchainresponsibility.step;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.payload.Response;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractStep implements ProcessStep {
    private ProcessStep nextStep;

    private ProcessStep errorStep;

    @Override
    public void setNext(ProcessStep nextStep) {
        this.nextStep = nextStep;
    }

    @Override
    public void setErrorStep(ProcessStep errorStep) {
        this.errorStep = errorStep;
    }

    @Override
    public final Message process(Message message) {

        return processAndApplyNext(message)
                .map(processedMessage -> {
                    Message enrich;
                    try {
                        enrich = nextStep.process(processedMessage);
                    } catch (Exception e) {
                        ArrayList<String> errors = new ArrayList<>();
                        errors.add(e.getMessage());
                        Response response = Response.builder().errors(errors).build();
                        Map<String, Response> responseMap = processedMessage.getResponseMap();
                        responseMap.clear();
                        responseMap.put(getStepName(), response);
                        enrich = errorStep.process(processedMessage);
                    }
                    return enrich;
                })
                .orElseGet(() -> nextStep.process(message));
    }

    protected abstract Optional<Message> processAndApplyNext(Message message);
}
