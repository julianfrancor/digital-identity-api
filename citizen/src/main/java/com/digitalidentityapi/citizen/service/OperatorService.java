package com.digitalidentityapi.citizen.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OperatorService {
    public String getOperators() {
        WebClient webClient = WebClient.create();
        String apiUrl = "https://govcarpeta-21868b7e9dd3.herokuapp.com/apis/getOperators";
        String responseBody = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("Respuesta: " + responseBody);
        return responseBody;
    }
}
