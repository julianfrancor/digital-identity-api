package com.digitalidentityapi.citizen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Citizen configuration
    @Bean
    Queue citizenQueue() {
        return new Queue("citizen", true);
    }

    @Bean
    TopicExchange citizenExchange() {
        return new TopicExchange("citizenExchange");
    }

    @Bean
    Binding citizenBinding(Queue citizenQueue, TopicExchange citizenExchange) {
        return BindingBuilder.bind(citizenQueue).to(citizenExchange).with("citizen.#");
    }

    // CitizenSubscription configuration
    @Bean
    Queue citizenSubscriptionQueue() {
        return new Queue("citizenSubscription", true);
    }

    @Bean
    TopicExchange citizenSubscriptionExchange() {
        return new TopicExchange("citizenSubscriptionExchange");
    }

    @Bean
    Binding citizenSubscriptionBinding(Queue citizenSubscriptionQueue, TopicExchange citizenSubscriptionExchange) {
        return BindingBuilder.bind(citizenSubscriptionQueue).to(citizenSubscriptionExchange).with("citizenSubscription.#");
    }

    // DigitalIdentityServices configuration
    @Bean
    Queue digitalIdentityServicesQueue() {
        return new Queue("digitalIdentityServices", true);
    }

    @Bean
    TopicExchange digitalIdentityServicesExchange() {
        return new TopicExchange("digitalIdentityServicesExchange");
    }

    @Bean
    Binding digitalIdentityServicesBinding(Queue digitalIdentityServicesQueue, TopicExchange digitalIdentityServicesExchange) {
        return BindingBuilder.bind(digitalIdentityServicesQueue).to(digitalIdentityServicesExchange).with("digitalIdentityServices.#");
    }

    // Document configuration
    @Bean
    Queue documentQueue() {
        return new Queue("document", true);
    }

    @Bean
    TopicExchange documentExchange() {
        return new TopicExchange("documentExchange");
    }

    @Bean
    Binding documentBinding(Queue documentQueue, TopicExchange documentExchange) {
        return BindingBuilder.bind(documentQueue).to(documentExchange).with("document.#");
    }

    // ExternalCompany configuration
    @Bean
    Queue externalCompanyQueue() {
        return new Queue("externalCompany", true);
    }

    @Bean
    TopicExchange externalCompanyExchange() {
        return new TopicExchange("externalCompanyExchange");
    }

    @Bean
    Binding externalCompanyBinding(Queue externalCompanyQueue, TopicExchange externalCompanyExchange) {
        return BindingBuilder.bind(externalCompanyQueue).to(externalCompanyExchange).with("externalCompany.#");
    }

    // OfferedSolution configuration
    @Bean
    Queue offeredSolutionQueue() {
        return new Queue("offeredSolution", true);
    }

    @Bean
    TopicExchange offeredSolutionExchange() {
        return new TopicExchange("offeredSolutionExchange");
    }

    @Bean
    Binding offeredSolutionBinding(Queue offeredSolutionQueue, TopicExchange offeredSolutionExchange) {
        return BindingBuilder.bind(offeredSolutionQueue).to(offeredSolutionExchange).with("offeredSolution.#");
    }

    // Transfers configuration
    @Bean
    Queue transfersQueue() {
        return new Queue("transfers", true);
    }

    @Bean
    TopicExchange transfersExchange() {
        return new TopicExchange("transfersExchange");
    }

    @Bean
    Binding transfersBinding(Queue transfersQueue, TopicExchange transfersExchange) {
        return BindingBuilder.bind(transfersQueue).to(transfersExchange).with("transfers.#");
    }
}
