package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.entity.CitizenUnregister;
import com.digitalidentityapi.operators.entity.NotificationMessage;
import com.digitalidentityapi.operators.service.UnregistrerCitizenServices;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UnregisterCitizen implements UnregistrerCitizenServices {

    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    public UnregisterCitizen(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }

    @Override
    public void unregisterCitizen(String message) {

    }
}
