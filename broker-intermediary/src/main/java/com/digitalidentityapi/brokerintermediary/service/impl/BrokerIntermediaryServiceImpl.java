package com.digitalidentityapi.brokerintermediary.service.impl;

import com.digitalidentityapi.brokerintermediary.Message.*;
import com.digitalidentityapi.brokerintermediary.dto.*;
import com.digitalidentityapi.brokerintermediary.producer.RabbitPublishMessage;
import com.digitalidentityapi.brokerintermediary.service.IBrokerIntermediaryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.digitalidentityapi.brokerintermediary.constants.Constants.*;

@Service
@AllArgsConstructor
public class BrokerIntermediaryServiceImpl implements IBrokerIntermediaryService {

    @Autowired
    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleCitizenOperations(String operation, CitizenDto citizenDto) {
        CitizenMessage citizenMessage = new CitizenMessage(operation, citizenDto);
        String citizenMessageString = getCitizenMessageString(citizenMessage);
        System.out.println(citizenMessage.getOperation());
        rabbitPublishMessage.sendMessageToQueue(CITIZEN_QUEUE, citizenMessageString);
        System.out.println("Message successfully published in Citizen Queue");
    }

    private String getCitizenMessageString(CitizenMessage citizenMessage) {
        String message = "";
        try {
            message = objectMapper.writeValueAsString(citizenMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public void handleDocumentOperations(String operation, DocumentDto documentDto) {
        DocumentMessage documentMessage = new DocumentMessage(operation, documentDto);
        System.out.println(documentMessage.getOperation());
        DocumentUploadMessage documentUploadMessage = new DocumentUploadMessage(
                documentDto.getTitle(),
                documentDto.getDocumentTypeId(),
                documentDto.getCitizenEmail(),
                documentDto.getBase64file()
        );
        rabbitPublishMessage.sendMessageToQueue(DOCUMENT_UPLOAD_QUEUE, getDocumentUploadMessageString(documentUploadMessage));
        System.out.println("Message successfully published in Document Queue");
    }

    private String getDocumentUploadMessageString(DocumentUploadMessage documentUploadMessage) {
        String message = "";
        try {
            message = objectMapper.writeValueAsString(documentUploadMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public void handleTransferOperations(TransferRequestDto transferRequestDto) {
        TransferMessage transferMessage = new TransferMessage(transferRequestDto);
        System.out.println(transferMessage.getCitizenEmail());
        rabbitPublishMessage.sendMessageToQueue(TRANSFERS_QUEUE, getTransferMessageString(transferMessage));
        System.out.println("Message successfully published in Transfers Queue");
    }

    @Override
    public void handleSignDocumentOperations(SignDocumentDto signDocumentDto){
        SignDocumentMessage signDocumentMessage = new SignDocumentMessage(signDocumentDto);
        System.out.println(signDocumentMessage.getEmail());
        rabbitPublishMessage.sendMessageToQueue(SIGN_DOCUMENTS_QUEUE, getSignDocumentMessageString(signDocumentMessage));
        System.out.println("Message successfully published in Sign Documents Queue");
    }

    private String getSignDocumentMessageString(SignDocumentMessage signDocumentMessage) {
        String message = "";
        try {
            message = objectMapper.writeValueAsString(signDocumentMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String getTransferMessageString(TransferMessage transferMessage) {
        String message = "";
        try {
            message = objectMapper.writeValueAsString(transferMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
