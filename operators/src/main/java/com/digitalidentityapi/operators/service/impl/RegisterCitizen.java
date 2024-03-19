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

import static com.digitalidentityapi.operators.constants.Constants.IDOPERATOR;
import static com.digitalidentityapi.operators.constants.Constants.OPERATORNAME;

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
        CitizenRegister citizenRegister = new CitizenRegister(json.getBigInteger("id"), json.getString("name"), json.getString("address"), json.getString("email"), IDOPERATOR, OPERATORNAME);
        System.out.println(citizenRegister.toString());
        Mono<String> response = operator.post()
                .uri(Constants.REGISTERCITIZEN)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(citizenRegister)
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(
                // Manejo del caso de éxito
                responseBody -> {
                    System.out.println("Respuesta exitosa del servicio: " + responseBody);
                    NotificationMessage notificationMessage = new NotificationMessage(json.getString("email"), responseBody);
                    rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
                },
                // Manejo del error
                error -> {
                    System.out.println("Error al llamar al servicio: " + error.getMessage());
                    NotificationMessage notificationMessage = new NotificationMessage(json.getString("email"), "El ciudadano que se intenta registrar con esta información: " + citizenRegister.toString() + "ya se encuentra registrado en otro Operador.");
                    rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
                }
        );
    }
}
