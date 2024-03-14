package com.digitalidentityapi.citizen.listener;

import com.digitalidentityapi.citizen.dto.OfferedSolutionDto;
import com.digitalidentityapi.citizen.service.IOfferedSolutionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OfferedSolutionMessageListener {

    @Autowired
    private IOfferedSolutionService offeredSolutionService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "offeredSolution")
    public void handleOfferedSolutionMessage(@Payload String message) {
        try {
            OfferedSolutionMessage offeredSolutionMessage = objectMapper.readValue(message, OfferedSolutionMessage.class);
            OfferedSolutionDto offeredSolutionDto = offeredSolutionMessage.getOfferedSolutionDto();

            switch (offeredSolutionMessage.getOperation()) {
                case "CREATE":
                    offeredSolutionService.createOfferedSolution(offeredSolutionDto);
                    break;
                case "UPDATE":
                    offeredSolutionService.updateOfferedSolution(offeredSolutionDto.getId(), offeredSolutionDto);
                    break;
                case "DELETE":
                    offeredSolutionService.deleteOfferedSolution(offeredSolutionDto.getId());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + offeredSolutionMessage.getOperation());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing offered solution message", e);
        }
    }

    @Getter
    @Setter
    public static class OfferedSolutionMessage {
        private String operation;
        private OfferedSolutionDto offeredSolutionDto;
    }
}
