package com.example.springchainresponsibility.service.step;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.payload.Response;
import com.example.springchainresponsibility.step.AbstractStep;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Order(0)
public class CountryByIpService extends AbstractStep {

    @Override
    protected Optional<Message> processAndApplyNext(Message message) {
        String ip = message.getRequestMap().get("ip");
        Response response;

        if ("105.236.111.22".equals(ip)) {
            response = Response.builder().answers(List.of("Country : Italy")).build();
            message.getResponseMap().put("countryByIp", response);
            return Optional.of(message);
        }

        if (ip.startsWith("192.") || ip.startsWith("172.")) {
            response = Response.builder().tickets(List.of("Ticket : Wrong Ip range")).build();
            message.getResponseMap().put("countryByIp", response);
            return Optional.of(message);
        }

        response = Response.builder().errors(List.of("Error : Not Valid Ip")).build();
        message.getResponseMap().put("countryByIp", response);

        return Optional.of(message);
    }

    @Override
    public String getStepName() {
        return "countryByIp";
    }
}
