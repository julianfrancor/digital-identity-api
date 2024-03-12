package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.service.SignDocumentsServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class registerTransferEndPointListener {

    @Autowired
    private SignDocumentsServices signDocumentsServices;

    @RabbitListener(queues = "registerTransferEndPoint")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido desde la cola registerTransferEndPoint: " + message);
        signDocumentsServices.signDocument(message);


    }
}
