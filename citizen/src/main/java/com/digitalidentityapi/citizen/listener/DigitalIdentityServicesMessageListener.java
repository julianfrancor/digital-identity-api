package com.digitalidentityapi.citizen.listener;

import com.digitalidentityapi.citizen.dto.DigitalIdentityServicesDto;
import com.digitalidentityapi.citizen.service.IDigitalIdentityServicesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DigitalIdentityServicesMessageListener {

    @Autowired
    private IDigitalIdentityServicesService digitalIdentityServicesService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "digitalIdentityServices")
    public void handleDigitalIdentityServicesMessage(@Payload String message) {
        try {
            DigitalIdentityServicesMessage servicesMessage = objectMapper.readValue(message, DigitalIdentityServicesMessage.class);

            switch (servicesMessage.getOperation()) {
                case "CREATE":
                    digitalIdentityServicesService.createService(servicesMessage.getDigitalIdentityServicesDto());
                    break;
                case "UPDATE":
                    digitalIdentityServicesService.updateService(servicesMessage.getDigitalIdentityServicesDto().getId(), servicesMessage.getDigitalIdentityServicesDto());
                    break;
                case "DELETE":
                    digitalIdentityServicesService.deleteService(servicesMessage.getDigitalIdentityServicesDto().getId());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + servicesMessage.getOperation());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing digital identity services message", e);
        }
    }

    @Getter
    @Setter
    public static class DigitalIdentityServicesMessage {
        private String operation;
        private DigitalIdentityServicesDto digitalIdentityServicesDto;
    }
}
