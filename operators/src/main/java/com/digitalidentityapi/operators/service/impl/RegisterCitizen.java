package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.entity.CitizenRegister;
import com.digitalidentityapi.operators.entity.NotificationMessage;
import com.digitalidentityapi.operators.service.RegisterCitizenServices;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RegisterCitizen implements RegisterCitizenServices {

    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    public RegisterCitizen(RabbitPublishMessage rabbitPublishMessage, GetOperators getOperators) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }


    @Override
    public void registerCitizen(String message) {
        JSONObject json = new JSONObject(message);
        System.out.println("Received: " + message);
        WebClient operator = WebClient.create(Constants.URL);
        CitizenRegister citizenRegister = new CitizenRegister(json.getBigInteger("id"), json.getString("name"), json.getString("address"), json.getString("email"), json.getString("operatorId"), json.getString("operatorName"));
        Mono<String> response = operator.post()
                .uri("/registerCitizen")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(citizenRegister)
                .retrieve()
                .bodyToMono(String.class);
        NotificationMessage notificationMessage = new NotificationMessage(json.getString("email"), response.block());
        rabbitPublishMessage.sendMessageToQueue("notificationQueue", notificationMessage.toString());
    }
}
