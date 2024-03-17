package com.digitalidentityapi.brokerintermediary.service.impl;

import com.digitalidentityapi.brokerintermediary.Message.CitizenMessage;
import com.digitalidentityapi.brokerintermediary.Message.DocumentMessage;
import com.digitalidentityapi.brokerintermediary.Message.TransferMessage;
import com.digitalidentityapi.brokerintermediary.dto.DocumentDto;
import com.digitalidentityapi.brokerintermediary.dto.TransferDto;
import com.digitalidentityapi.brokerintermediary.service.IBrokerIntermediaryService;
import com.digitalidentityapi.brokerintermediary.dto.CitizenDto;
import com.digitalidentityapi.brokerintermediary.producer.RabbitPublishMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static com.digitalidentityapi.brokerintermediary.constants.Constants.*;

@Service
@AllArgsConstructor
public class BrokerIntermediaryServiceImpl implements IBrokerIntermediaryService {

    @Autowired
    private final RabbitPublishMessage rabbitPublishMessage;

    @Override
    public void handleCitizenOperations(String operation, CitizenDto citizenDto) {
        CitizenMessage citizenMessage = new CitizenMessage(operation, citizenDto);
        System.out.println(citizenMessage.getOperation());
        rabbitPublishMessage.sendMessageToQueue(CITIZEN_QUEUE, String.valueOf(citizenMessage));
        System.out.println("Message successfully published in Citizen Queue");
    }

    @Override
    public void handleDocumentOperations(String operation, DocumentDto documentDto) {
        DocumentMessage documentMessage = new DocumentMessage(operation, documentDto);
        System.out.println(documentMessage.getOperation());
        rabbitPublishMessage.sendMessageToQueue(DOCUMENT_QUEUE, String.valueOf(documentMessage));
        System.out.println("Message successfully published in Document Queue");
    }

    @Override
    public void handleTransferOperations(TransferDto transferDto) {
        TransferMessage transferMessage = new TransferMessage("CREATE", transferDto);
        System.out.println(transferMessage.getOperation());
        rabbitPublishMessage.sendMessageToQueue(TRANSFERS_QUEUE, String.valueOf(transferMessage));
        System.out.println("Message successfully published in Transfers Queue");
    }
}
