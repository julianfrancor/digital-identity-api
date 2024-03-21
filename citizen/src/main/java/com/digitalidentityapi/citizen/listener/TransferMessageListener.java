package com.digitalidentityapi.citizen.listener;

import com.digitalidentityapi.citizen.dto.TransferDto;
import com.digitalidentityapi.citizen.dto.TransferRequestDto;
import com.digitalidentityapi.citizen.service.ITransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransferMessageListener {

    @Autowired
    private ITransferService transferService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "transfers")
    public void handleMessage(@Payload String message) {
        try {
            TransferRequestDto transferRequestDto = objectMapper.readValue(message, TransferRequestDto.class);
            transferService.createTransfer(transferRequestDto);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing message", e);
        }
    }

    @Getter
    @Setter
    public static class TransferMessage {
        private String operation;
        private TransferDto transferDto;
    }
}
