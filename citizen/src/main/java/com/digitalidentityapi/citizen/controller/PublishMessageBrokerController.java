package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.Message.CitizenMessage;
import com.digitalidentityapi.citizen.Message.DocumentMessage;
import com.digitalidentityapi.citizen.Message.NotificationMessage;
import com.digitalidentityapi.citizen.Message.RegisterCitizenMessage;
import com.digitalidentityapi.citizen.producer.RabbitPublishMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.digitalidentityapi.citizen.constants.Constants.*;

@RestController
@RequestMapping(path = "/publish-broker-message", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PublishMessageBrokerController {

    @Autowired
    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public PublishMessageBrokerController(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }

    @PostMapping("/publish-to-citizen-queue")
    public ResponseEntity<String> publishMessageToCitizenQueue(@Valid @RequestBody String message) throws JsonProcessingException {
        System.out.println(message);
        CitizenMessage citizenMessage = objectMapper.readValue(message, CitizenMessage.class);
        System.out.println(citizenMessage.getOperation());
        rabbitPublishMessage.sendMessageToQueue(CITIZEN_QUEUE, message);
        return ResponseEntity.ok("Message successfully published in Citizen Queue");
    }

    @PostMapping("/publish-to-notification-queue")
    public ResponseEntity<String> publishMessageToNotificationQueue(@Valid @RequestBody String message) throws JsonProcessingException {
        System.out.println(message);
        NotificationMessage citizenMessage = objectMapper.readValue(message, NotificationMessage.class);
        System.out.println(citizenMessage.getMessage());
        rabbitPublishMessage.sendMessageToQueue(NOTIFICATION_QUEUE, message);
        return ResponseEntity.ok("Message successfully published to Notification Queue");
    }

    @PostMapping("/publish-to-register-citizen-queue")
    public ResponseEntity<String> publishMessageToRegisterCitizenQueue(@Valid @RequestBody String message) throws JsonProcessingException {
        System.out.println(message);
        RegisterCitizenMessage registerCitizenMessage = objectMapper.readValue(message, RegisterCitizenMessage.class);
        System.out.println(registerCitizenMessage.getEmail());
        rabbitPublishMessage.sendMessageToQueue(REGISTER_CITIZEN_QUEUE, message);
        return ResponseEntity.ok("Message successfully published to Operators Queue");
    }

    @PostMapping("/publish-to-document-queue")
    public ResponseEntity<String> publishMessageToDocumentQueue(@Valid @RequestBody String message) throws JsonProcessingException {
        System.out.println(message);
        DocumentMessage documentMessage = objectMapper.readValue(message, DocumentMessage.class);
        System.out.println(documentMessage.getOperation());
        rabbitPublishMessage.sendMessageToQueue(DOCUMENT_QUEUE, message);
        return ResponseEntity.ok("Message successfully published to Documents Queue");
    }
}
