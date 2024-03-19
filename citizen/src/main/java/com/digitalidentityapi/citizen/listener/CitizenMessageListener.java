package com.digitalidentityapi.citizen.listener;

import com.digitalidentityapi.citizen.Message.CitizenMessage;
import com.digitalidentityapi.citizen.dto.CitizenDto;
import com.digitalidentityapi.citizen.service.ICitizenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CitizenMessageListener {

    @Autowired
    private ICitizenService citizenService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "citizen")
    public void handleCitizenMessage(@Payload String message) {
        try {
            System.out.println("===========" + message + "===========");
            CitizenMessage citizenMessage = objectMapper.readValue(message, CitizenMessage.class);

            switch (citizenMessage.getOperation()) {
                case "CREATE":
                    citizenService.createCitizen(citizenMessage.getCitizenDto());
                    break;
                case "UPDATE":
                    citizenService.updateCitizen(citizenMessage.getCitizenDto().getEmail(), citizenMessage.getCitizenDto());
                    break;
                case "DELETE":
                    citizenService.deleteCitizen(citizenMessage.getCitizenDto().getEmail());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + citizenMessage.getOperation());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing message", e);
        }
    }
}
