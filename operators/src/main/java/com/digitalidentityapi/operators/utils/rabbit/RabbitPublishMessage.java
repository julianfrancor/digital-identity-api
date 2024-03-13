package com.digitalidentityapi.operators.utils.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitPublishMessage {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitPublishMessage(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessageToQueue(String queueName, String message) {
        amqpTemplate.convertAndSend(queueName, message);
        System.out.println("Mensaje enviado a la cola '" + queueName + "': " + message);
    }


}