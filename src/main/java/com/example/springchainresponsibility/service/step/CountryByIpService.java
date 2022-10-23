package com.example.springchainresponsibility.service.step;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.payload.Response;
import com.example.springchainresponsibility.step.AbstractStep;
import com.example.springchainresponsibility.step.base.ErrorStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Order(0)
public class CountryByIpService extends AbstractStep {

    public CountryByIpService(@Autowired ErrorStep errorStep) {
        super.setErrorStep(errorStep);
    }

    @Override
    protected Optional<Message> processAndApplyNext(Message message) {
        String ip = message.getRequestMap().get("ip");
        Response response;

        Map<String, Response> responseMap = message.getResponseMap();

        if ("105.236.111.22".equals(ip)) {
            response = Response.builder().answers(List.of("Country : Italy")).build();
            responseMap.put("countryByIp", response);


            return Optional.of(message);
        }

        if (ip.startsWith("192.") || ip.startsWith("172.")) {
            response = Response.builder().tickets(List.of("Ticket : Wrong Ip range")).build();
            responseMap.clear();
            responseMap.put("countryByIp", response);
            message.setNextStep(false);
            return Optional.of(message);
        }

        response = Response.builder().errors(List.of("Error : Not Valid Ip")).build();
        responseMap.clear();
        message.setNextStep(false);
        responseMap.put("countryByIp", response);

        return Optional.of(message);
    }

    @Override
    public String getStepName() {
        return "countryByIp";
    }
}
