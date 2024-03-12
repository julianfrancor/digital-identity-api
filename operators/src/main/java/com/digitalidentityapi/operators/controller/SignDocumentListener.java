package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.service.SignDocumentsServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignDocumentListener {

    @Autowired
    private SignDocumentsServices signDocumentsServices;

    @RabbitListener(queues = "signDocuments")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido de la cola desde sign document: " + message);
        signDocumentsServices.signDocument(message);


    }
}
