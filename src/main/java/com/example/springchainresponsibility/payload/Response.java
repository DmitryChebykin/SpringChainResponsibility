package com.example.springchainresponsibility.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Response {
    private List<String> errors;

    private List<String> tickets;

    private List<String> answers;
}
