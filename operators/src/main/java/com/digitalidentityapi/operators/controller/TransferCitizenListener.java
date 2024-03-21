package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.dto.CitizenForTransferDTO;
import com.digitalidentityapi.operators.service.TransferCitizenServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class TransferCitizenListener {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TransferCitizenServices transferCitizenServices;

    @RabbitListener(queues = "transferCitizen")
    public void receiveMessage(@Valid String message) throws JsonProcessingException {
        CitizenForTransferDTO recieveCitizenForTransfer = objectMapper.readValue(message, CitizenForTransferDTO.class);
        System.out.println(recieveCitizenForTransfer.getCitizenWithDocumentsTransferInfoDTO().getId());
        transferCitizenServices.transferCitizen(recieveCitizenForTransfer);

    }
}
