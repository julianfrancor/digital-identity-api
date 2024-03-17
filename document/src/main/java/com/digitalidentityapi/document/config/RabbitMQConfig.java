package com.digitalidentityapi.document.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.digitalidentityapi.document.constants.documentConstants.*;

@Configuration
public class RabbitMQConfig {

    // Upload Document configuration
    @Bean
    Queue uploadDocumentQueue() {
        return new Queue(UPLOADDOCUMETQUEUE, true);
    }

    @Bean
    TopicExchange citizenExchange() {
        return new TopicExchange(UPLOADDOCUMETEXCHANGE);
    }

    @Bean
    Binding uploadDocumentBinding(Queue uploadDocumentQueue, TopicExchange citizenExchange) {
        return BindingBuilder.bind(uploadDocumentQueue).to(citizenExchange).with(UPLOADDOCUMETQUEUE + "." + ROUTING_KEY_PATTERN);
    }


}
