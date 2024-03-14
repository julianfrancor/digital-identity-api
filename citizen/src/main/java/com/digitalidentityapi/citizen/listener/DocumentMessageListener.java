package com.digitalidentityapi.citizen.listener;

import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.service.IDocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentMessageListener {

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "document")
    public void handleDocumentMessage(@Payload String message) {
        try {
            DocumentMessage documentMessage = objectMapper.readValue(message, DocumentMessage.class);

            switch (documentMessage.getOperation()) {
                case "CREATE":
                    documentService.createDocument(documentMessage.getDocumentDto());
                    break;
                case "UPDATE":
                    documentService.updateDocument(documentMessage.getDocumentDto().getId(), documentMessage.getDocumentDto());
                    break;
                case "DELETE":
                    documentService.deleteDocument(documentMessage.getDocumentDto().getId());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + documentMessage.getOperation());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing document message", e);
        }
    }

    @Getter
    @Setter
    public static class DocumentMessage {
        private String operation;
        private DocumentDto documentDto;
    }
}
