package com.digitalidentityapi.citizen.listener;

import com.digitalidentityapi.citizen.dto.ExternalCompanyDto;
import com.digitalidentityapi.citizen.service.IExternalCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExternalCompanyServiceMessageListener {

    @Autowired
    private IExternalCompanyService externalCompanyService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "externalCompany")
    public void handleMessage(@Payload String message) {
        try {
            ExternalCompanyMessage externalCompanyMessage = objectMapper.readValue(message, ExternalCompanyMessage.class);
            ExternalCompanyDto externalCompanyDto = externalCompanyMessage.getExternalCompanyDto();

            switch (externalCompanyMessage.getOperation()) {
                case "CREATE":
                    externalCompanyService.createExternalCompany(externalCompanyDto);
                    break;
                case "UPDATE":
                    externalCompanyService.updateExternalCompany(externalCompanyDto.getId(), externalCompanyDto);
                    break;
                case "DELETE":
                    externalCompanyService.deleteExternalCompany(externalCompanyDto.getId());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + externalCompanyMessage.getOperation());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing external company message", e);
        }
    }

    @Getter
    @Setter
    public static class ExternalCompanyMessage {
        private String operation;
        private ExternalCompanyDto externalCompanyDto;
    }
}
