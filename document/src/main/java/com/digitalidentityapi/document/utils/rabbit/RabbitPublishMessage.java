package com.digitalidentityapi.document.utils.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.digitalidentityapi.document.constants.documentConstants.DOCUMENTUPDATEQUEUE;
import static com.digitalidentityapi.document.constants.documentConstants.ROUTING_KEY_PATTERN;

@Component
public class RabbitPublishMessage {

    private final AmqpTemplate amqpTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitPublishMessage(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessageToQueue(String queueName, String message) {
        amqpTemplate.convertAndSend(queueName, message);
        System.out.println("Mensaje enviado a la cola '" + queueName + "': " + message);
    }

    public void sendMessageToQueueUpdate(String queueName, String message) {
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) {
                message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
                return message;
            }
        };
        rabbitTemplate.convertAndSend("documentExchange", DOCUMENTUPDATEQUEUE, message, messagePostProcessor);
        System.out.println("Mensaje enviado a la cola '" + queueName + "': " + message);
    }
}

