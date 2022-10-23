package com.example.springchainresponsibility.service.step;

import com.example.springchainresponsibility.payload.Message;
import com.example.springchainresponsibility.payload.Response;
import com.example.springchainresponsibility.service.CardInfoService;
import com.example.springchainresponsibility.step.AbstractStep;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@Order(2)
public class CardByIdService extends AbstractStep {
    private final CardInfoService cardInfoService;

    public CardByIdService(CardInfoService cardInfoService) {
        this.cardInfoService = cardInfoService;
    }

    @Override
    protected Optional<Message> processAndApplyNext(Message message) {
        String cardId = message.getRequestMap().get("cardId");
        BigDecimal balance = cardInfoService.getBalance(cardId);

        Response response = Response.builder().answers(List.of("Card balance : " + balance)).build();
        message.getResponseMap().put("cardById", response);

        return Optional.of(message);
    }

    @Override
    public String getStepName() {
        return "cardById";
    }
}
