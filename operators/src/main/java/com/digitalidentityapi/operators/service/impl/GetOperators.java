package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.service.GetOperatorsServices;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.digitalidentityapi.operators.constants.Constants.GETOPERATORS;

@Service
public class GetOperators implements GetOperatorsServices {
    @Override
    public String getOperators() {
        WebClient webClient = WebClient.create(Constants.URL);
        Mono<String> responseBody = webClient.get()
                .uri(GETOPERATORS)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println("Respuesta: " + responseBody);
        return responseBody.block();
    }
}
