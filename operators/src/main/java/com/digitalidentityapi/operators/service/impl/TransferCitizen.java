package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.entity.AuthenticDocument;
import com.digitalidentityapi.operators.entity.NotificationMessage;
import com.digitalidentityapi.operators.service.SignDocumentsServices;
import com.digitalidentityapi.operators.service.TransferCitizenServices;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TransferCitizen implements TransferCitizenServices {
    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    public TransferCitizen(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }


    @Override
    public void transferCitizzen(String message) {
        JSONObject json = new JSONObject(message);
        System.out.println("Received: " + message);
        WebClient operator = WebClient.create(Constants.URL);
        Mono<String> response = operator.put()
                .uri("/authenticateDocument")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(response.block());
        NotificationMessage notificationMessage = new NotificationMessage(json.getString("email"), response.block());
        rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
    }
}
