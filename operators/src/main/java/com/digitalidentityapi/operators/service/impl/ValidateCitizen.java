package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.service.ValidateCitizenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.digitalidentityapi.operators.constants.Constants.*;

@Service
public class ValidateCitizen implements ValidateCitizenServices {

    @Override
    public String validateCitizen(String id) {
        WebClient webClient = WebClient.create();
        URI uri = UriComponentsBuilder.fromUriString(Constants.URL + PATHVALIDATECITIZEN + id)
                .build().toUri();
        Mono<String> responseBody = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
        responseBody.subscribe(System.out::println);
        if (responseBody.block() != null) {
            return EXISTCLIENT;
        } else {
            System.out.println(responseBody.block());
            return NOEXISTCLIENT;
        }
    }
}


