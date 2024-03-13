package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.service.RegisterCitizenServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferCitizenListener {

    @Autowired
    private RegisterCitizenServices registerCitizenServices;

    @RabbitListener(queues = "transferCitizen")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido desde la cola transferCitizen: " + message);
        registerCitizenServices.registerCitizen(message);

    }
}
