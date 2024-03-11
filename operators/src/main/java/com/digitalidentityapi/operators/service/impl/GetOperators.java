package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GetOperators {
    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    public GetOperators(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }

    public void getOperators() {
        WebClient webClient = WebClient.create(Constants.URL);
        Mono<String> responseBody = webClient.get()
                .uri("/getOperators")
                .retrieve()
                .bodyToMono(String.class);
        System.out.println("Respuesta: " + responseBody);
        rabbitPublishMessage.sendMessageToQueue("getOperators", responseBody.block());
    }


}
