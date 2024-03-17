package com.digitalidentityapi.operators.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.digitalidentityapi.operators.constants.Constants.*;

@Configuration
public class RabbitMQConfig {

    // Citizen configuration
    @Bean
    Queue signDocumentsQueue() {
        return new Queue(SIGNDOCUMENTSQUEUE, true);
    }

    @Bean
    TopicExchange signDocumentsExchange() {
        return new TopicExchange(SIGNDOCUMENTSEXCHANGE);
    }

    @Bean
    Binding signDocumentsBinding(Queue signDocumentsQueue, TopicExchange signDocumentsExchange) {
        return BindingBuilder.bind(signDocumentsQueue).to(signDocumentsExchange).with(SIGNDOCUMENTSQUEUE + "." + ROUTING_KEY_PATTERN);
    }


}
