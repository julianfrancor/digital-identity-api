package com.digitalidentityapi.citizen.listener;

import com.digitalidentityapi.citizen.dto.CitizenSubscriptionDto;
import com.digitalidentityapi.citizen.service.ICitizenSubscriptionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CitizenSubscriptionMessageListener {

    @Autowired
    private ICitizenSubscriptionService citizenSubscriptionService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "citizenSubscription")
    public void handleCitizenSubscriptionMessage(@Payload String message) {
        try {
            CitizenSubscriptionMessage subscriptionMessage = objectMapper.readValue(message, CitizenSubscriptionMessage.class);
            CitizenSubscriptionDto subscriptionDto = subscriptionMessage.getCitizenSubscriptionDto();

            switch (subscriptionMessage.getOperation()) {
                case "SUBSCRIBE":
                    citizenSubscriptionService.subscribeService(subscriptionDto.getCitizenEmail(), subscriptionDto.getDigitalIdentityServiceId());
                    break;
                case "UNSUBSCRIBE":
                    citizenSubscriptionService.unsubscribeService(subscriptionDto.getCitizenEmail(), subscriptionDto.getDigitalIdentityServiceId());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + subscriptionMessage.getOperation());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing citizen subscription message", e);
        }
    }

    @Getter
    @Setter
    public static class CitizenSubscriptionMessage {
        private String operation;
        private CitizenSubscriptionDto citizenSubscriptionDto;
    }
}
