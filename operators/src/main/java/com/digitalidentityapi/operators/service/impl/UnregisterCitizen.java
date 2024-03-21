package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.entity.NotificationMessage;
import com.digitalidentityapi.operators.service.UnregistrerCitizenServices;
import com.digitalidentityapi.operators.utils.rabbit.BuildRequestUnregisterCitizen;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnregisterCitizen implements UnregistrerCitizenServices {

    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    public UnregisterCitizen(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }

    @Override
    public void unregisterCitizen(String message) {
        JSONObject json = new JSONObject(message);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = "{\"id\": " + json.getInt("id") + "}";
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = BuildRequestUnregisterCitizen.getRequest(body);
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if (response.code() == 200) {
                System.out.println("Respuesta del servidor: " + responseBody);
                NotificationMessage notificationMessage = new NotificationMessage(json.getString("email"), responseBody);
                rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
            } else {
                NotificationMessage notificationMessage = new NotificationMessage(json.getString("email"), "Error Borrando Registro - Ciudadano no registrado en Operador Identidad Digital");
                rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

