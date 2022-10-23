package com.example.springchainresponsibility;

import com.example.springchainresponsibility.mapper.ResponseMapper;
import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.payload.Response;
import com.example.springchainresponsibility.service.ProcessFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class SpringChainResponsibilityApplication {
    final ProcessFacade processFacade;

    final ResponseMapper responseMapper;

    public SpringChainResponsibilityApplication(ProcessFacade processFacade, ResponseMapper responseMapper) {
        this.processFacade = processFacade;
        this.responseMapper = responseMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringChainResponsibilityApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onStart() {
        processMessage(Map.of(
                "ip", "105.236.111.22",
                "userId", "086-40-4550",
                "cardId", "1234-2121-1221-1211"
        ));

        processMessage(Map.of(
                "ip", "192.236.111.22",
                "userId", "086-40-4550",
                "cardId", "1234-2121-1221-1211"
        ));

        processMessage(Map.of(
                "ip", "105.236.111.22",
                "userId", "000-00-000",
                "cardId", "1234-2121-1221-1211"
        ));

        processMessage(Map.of(
                "ip", "192.236.111.22",
                "userId", "086-40-4550",
                "cardId", "1111-2121-1221-1211"
        ));
    }

    private void processMessage(Map<String, String> requestMap) {
        Message startMessage = Message.builder()
                .requestMap(requestMap)
                .isNextStep(true)
                .build();

        Message message = processFacade.startChain(
                startMessage);

        log.info("Result message: {}", message);

        List<Response> responses = new ArrayList<>();

        responses.add(Response.builder()
                .answers(new ArrayList<>())
                .errors(new ArrayList<>())
                .tickets(new ArrayList<>())
                .build());

        responses.addAll(message.getResponseMap().values().stream().toList());
        Response reduce = responses.stream().reduce(responses.get(0), (e1, e2) -> {
            Response response = responseMapper.updateResponse(e1, e2);
            return e1;
        });

        log.info("Result response: {}", reduce);
    }
}
