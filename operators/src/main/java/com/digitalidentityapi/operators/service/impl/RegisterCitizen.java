package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.entity.citizenRegister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class RegisterCitizen {


    public String registerCitizen(citizenRegister citizenRegister) {
        WebClient webClient = WebClient.builder()
                .baseUrl(Constants.URL)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient.post()
                .uri("/registerCitizen")
                .body(BodyInserters.fromValue(citizenRegister))
                .retrieve().bodyToMono(String.class).block();
    }
}
