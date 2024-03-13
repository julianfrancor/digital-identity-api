package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.entity.AuthenticDocument;
import com.digitalidentityapi.operators.entity.NotificationMessage;
import com.digitalidentityapi.operators.service.SignDocumentsServices;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SignDocument implements SignDocumentsServices {
    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    public SignDocument(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }

    @Override
    public void signDocument(String message) {
        JSONObject json = new JSONObject(message);
        System.out.println("Received: " + message);
        WebClient operator = WebClient.create(Constants.URL);
        AuthenticDocument authenticDocument = new AuthenticDocument(String.valueOf(json.getInt("idCitizen")), json.getString("UrlDocument"), json.getString("documentTitle"));
        Mono<String> response = operator.put()
                .uri("/authenticateDocument")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(authenticDocument)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(response.block());
        NotificationMessage notificationMessage = new NotificationMessage(json.getString("email"), response.block());
        rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
    }
}
