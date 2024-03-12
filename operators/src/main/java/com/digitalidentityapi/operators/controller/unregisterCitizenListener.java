package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.service.UnregistrerCitizenServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class unregisterCitizenListener {

    @Autowired
    private UnregistrerCitizenServices unregistrerCitizenServices;

    @RabbitListener(queues = "unregisterCitizen")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido desde la cola unregisterCitizen: " + message);
        unregistrerCitizenServices.unregisterCitizen(message);


    }
}
