package com.example.springchainresponsibility.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class Message {
    private Map<String, Response> responseMap;

    private Map<String, String> requestMap;

    private String metaInfo;

    boolean isNextStep;
}
