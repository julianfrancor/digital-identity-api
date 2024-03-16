package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.Message.CitizenMessage;
import com.digitalidentityapi.citizen.producer.RabbitPublishMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.digitalidentityapi.citizen.constants.Constants.CITIZEN_QUEUE;

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
    public ResponseEntity<String> publishMessage(@Valid @RequestBody String message) throws JsonProcessingException {
        System.out.println(message);
        CitizenMessage citizenMessage = objectMapper.readValue(message, CitizenMessage.class);
        System.out.println(citizenMessage.getOperation());
        rabbitPublishMessage.sendMessageToQueue(CITIZEN_QUEUE, message);
        return ResponseEntity.ok("Message successfully published in Citizen Queue");
    }
}
