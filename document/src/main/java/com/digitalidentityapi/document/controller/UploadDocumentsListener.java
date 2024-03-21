package com.digitalidentityapi.document.controller;

import com.digitalidentityapi.document.service.UploadDocumentI;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UploadDocumentsListener {
    @Autowired
    private UploadDocumentI uploadDocumentI;
    @RabbitListener(queues = "uploadDocumentQueue")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido desde la cola uploadDocument: " + message);
        uploadDocumentI.uploadDocument(message);

    }

}
