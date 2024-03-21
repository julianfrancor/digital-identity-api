package com.digitalidentityapi.operators.utils.rabbit;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.dto.RecieveCitizenDTO;
import com.digitalidentityapi.operators.entity.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SendCallBack {
    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    public SendCallBack(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }

    public void sendCallBack(RecieveCitizenDTO recieveCitizenDTO) {
        WebClient operator = WebClient.create(recieveCitizenDTO.callbackUrl);
        Mono<String> response = operator.post()
                .retrieve()
                .bodyToMono(String.class);
        response.subscribe(
                // Manejo del caso de éxito
                responseBody -> {
                    System.out.println("Respuesta exitosa del servicio: " + responseBody);
                    NotificationMessage notificationMessage = new NotificationMessage(recieveCitizenDTO.getEmail(), "Su proceso de traslado fue realizado exitosamente y su operador origen fue notificado con exito");
                    rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
                },
                // Manejo del error
                error -> {
                    System.out.println("Error al llamar al servicio: " + error.getMessage());
                    NotificationMessage notificationMessage = new NotificationMessage(recieveCitizenDTO.getEmail(), "Su proceso de traslado fue realizado exitosamente, pero su operador no esta disponible para notificarlo, sin embargo puede hacer uso de su Carpeta Identidad Digital.");
                    rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
                });
    }
}
