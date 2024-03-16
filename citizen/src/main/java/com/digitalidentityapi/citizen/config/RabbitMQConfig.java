package com.digitalidentityapi.citizen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.digitalidentityapi.citizen.constants.Constants.*;

@Configuration
public class RabbitMQConfig {

    // Citizen configuration
    @Bean
    Queue citizenQueue() {
        return new Queue(CITIZEN_QUEUE, true);
    }

    @Bean
    TopicExchange citizenExchange() {
        return new TopicExchange(CITIZEN_EXCHANGE);
    }

    @Bean
    Binding citizenBinding(Queue citizenQueue, TopicExchange citizenExchange) {
        return BindingBuilder.bind(citizenQueue).to(citizenExchange).with(CITIZEN_QUEUE + "." + ROUTING_KEY_PATTERN);
    }

    // CitizenSubscription configuration
    @Bean
    Queue citizenSubscriptionQueue() {
        return new Queue(CITIZEN_SUBSCRIPTION_QUEUE, true);
    }

    @Bean
    TopicExchange citizenSubscriptionExchange() {
        return new TopicExchange(CITIZEN_SUBSCRIPTION_EXCHANGE);
    }

    @Bean
    Binding citizenSubscriptionBinding(Queue citizenSubscriptionQueue, TopicExchange citizenSubscriptionExchange) {
        return BindingBuilder.bind(citizenSubscriptionQueue).to(citizenSubscriptionExchange).with(CITIZEN_SUBSCRIPTION_QUEUE + "." + ROUTING_KEY_PATTERN);
    }

    // DigitalIdentityServices configuration
    @Bean
    Queue digitalIdentityServicesQueue() {
        return new Queue(DIGITAL_IDENTITY_SERVICES_QUEUE, true);
    }

    @Bean
    TopicExchange digitalIdentityServicesExchange() {
        return new TopicExchange(DIGITAL_IDENTITY_SERVICES_EXCHANGE);
    }

    @Bean
    Binding digitalIdentityServicesBinding(Queue digitalIdentityServicesQueue, TopicExchange digitalIdentityServicesExchange) {
        return BindingBuilder.bind(digitalIdentityServicesQueue).to(digitalIdentityServicesExchange).with(DIGITAL_IDENTITY_SERVICES_QUEUE + "." + ROUTING_KEY_PATTERN);
    }

    // Document configuration
    @Bean
    Queue documentQueue() {
        return new Queue(DOCUMENT_QUEUE, true);
    }

    @Bean
    TopicExchange documentExchange() {
        return new TopicExchange(DOCUMENT_EXCHANGE);
    }

    @Bean
    Binding documentBinding(Queue documentQueue, TopicExchange documentExchange) {
        return BindingBuilder.bind(documentQueue).to(documentExchange).with(DOCUMENT_QUEUE + "." + ROUTING_KEY_PATTERN);
    }

    // ExternalCompany configuration
    @Bean
    Queue externalCompanyQueue() {
        return new Queue(EXTERNAL_COMPANY_QUEUE, true);
    }

    @Bean
    TopicExchange externalCompanyExchange() {
        return new TopicExchange(EXTERNAL_COMPANY_EXCHANGE);
    }

    @Bean
    Binding externalCompanyBinding(Queue externalCompanyQueue, TopicExchange externalCompanyExchange) {
        return BindingBuilder.bind(externalCompanyQueue).to(externalCompanyExchange).with(EXTERNAL_COMPANY_QUEUE + "." + ROUTING_KEY_PATTERN);
    }

    // OfferedSolution configuration
    @Bean
    Queue offeredSolutionQueue() {
        return new Queue(OFFERED_SOLUTION_QUEUE, true);
    }

    @Bean
    TopicExchange offeredSolutionExchange() {
        return new TopicExchange(OFFERED_SOLUTION_EXCHANGE);
    }

    @Bean
    Binding offeredSolutionBinding(Queue offeredSolutionQueue, TopicExchange offeredSolutionExchange) {
        return BindingBuilder.bind(offeredSolutionQueue).to(offeredSolutionExchange).with(OFFERED_SOLUTION_QUEUE + "." + ROUTING_KEY_PATTERN);
    }

    // Transfers configuration
    @Bean
    Queue transfersQueue() {
        return new Queue(TRANSFERS_QUEUE, true);
    }

    @Bean
    TopicExchange transfersExchange() {
        return new TopicExchange(TRANSFERS_EXCHANGE);
    }

    @Bean
    Binding transfersBinding(Queue transfersQueue, TopicExchange transfersExchange) {
        return BindingBuilder.bind(transfersQueue).to(transfersExchange).with(TRANSFERS_QUEUE + "." + ROUTING_KEY_PATTERN);
    }
}
