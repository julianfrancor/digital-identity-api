package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.service.ValidateCitizenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ValidateCitizen implements ValidateCitizenServices {

    @Override
    public String validateCitizen(String id) {
        WebClient webClient = WebClient.create(Constants.URL);
        Mono<String> responseBody = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/validateCitizen")
                        .queryParam("id", Long.getLong(id))
                        .build())
                .retrieve()
                .bodyToMono(String.class);
        System.out.println("Respuesta: " + responseBody);
        return responseBody.block();
    }
}
