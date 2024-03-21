package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.constants.Constants;
import com.digitalidentityapi.operators.dto.CitizenForTransferDTO;
import com.digitalidentityapi.operators.dto.UnregistredCitizenDTO;
import com.digitalidentityapi.operators.entity.CitizenRegister;
import com.digitalidentityapi.operators.entity.NotificationMessage;
import com.digitalidentityapi.operators.entity.Operator;
import com.digitalidentityapi.operators.service.GetOperatorsServices;
import com.digitalidentityapi.operators.service.RegisterCitizenServices;
import com.digitalidentityapi.operators.service.TransferCitizenServices;
import com.digitalidentityapi.operators.service.UnregistrerCitizenServices;
import com.digitalidentityapi.operators.utils.rabbit.BuildMessageTransfer;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static com.digitalidentityapi.operators.constants.Constants.*;

@Service
public class TransferCitizen implements TransferCitizenServices {
    private final RabbitPublishMessage rabbitPublishMessage;
    @Autowired
    GetOperatorsServices getOperatorsServices;
    @Autowired
    UnregistrerCitizenServices unregistrerCitizenServices;
    @Autowired
    RegisterCitizenServices registerCitizenServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public TransferCitizen(RabbitPublishMessage rabbitPublishMessage) {
        this.rabbitPublishMessage = rabbitPublishMessage;
    }


    @Override
    public void transferCitizen(CitizenForTransferDTO citizenForTransferDTO) {

        String urlTransfer = getUrlTranfer(citizenForTransferDTO.getDestinationOperatorId());
        WebClient operator = WebClient.create(urlTransfer);
        UnregistredCitizenDTO unregistredCitizenDTO = new UnregistredCitizenDTO(BigInteger.valueOf(Long.parseLong(citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getId())), citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getEmail());
        unregistrerCitizenServices.unregisterCitizen(unregistredCitizenDTO.toString());
        BuildMessageTransfer buildMessageTransfer = new BuildMessageTransfer();
        String body = buildMessageTransfer.buildMessageTransferDTO(citizenForTransferDTO);
        Mono<String> response = operator.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
        response.subscribe(
                // Manejo del caso de éxito
                responseBody -> {
                    System.out.println("Respuesta exitosa del servicio: " + responseBody);
                    NotificationMessage notificationMessage = new NotificationMessage(citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getEmail(), response.block());
                    rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
                },
                // Manejo del error
                error -> {
                    System.out.println("Error al llamar al servicio: " + error.getMessage());
                    NotificationMessage notificationMessage = new NotificationMessage(citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getEmail(), ERRORTRANSFERCITIZENOPERATOR);
                    rabbitPublishMessage.sendMessageToQueue(Constants.NOTIFICATIONSQUEU, notificationMessage.toString());
                    rollbackUnregisterCitizen(citizenForTransferDTO);
                }

        );

    }

    public String getUrlTranfer(String idOperator) {
        ObjectMapper mapper = new ObjectMapper();
        String urlTransfer = "";
        String operatorsMinTic = getOperatorsServices.getOperators();
        try {
            List<Operator> operators = mapper.readValue(operatorsMinTic, new TypeReference<List<Operator>>() {
            });

            for (Operator operator : operators) {
                if (operator.get_id().equals(idOperator)) {
                    System.out.println("Encontrado: " + operator.getOperatorName());
                    urlTransfer = operator.getTransferAPIURL();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlTransfer;
    }

    public void rollbackUnregisterCitizen(CitizenForTransferDTO citizenForTransferDTO) {
        CitizenRegister citizenRegister = new CitizenRegister(BigInteger.valueOf(Long.parseLong(citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getId())),
                citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getName(),
                citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getAddress(),
                citizenForTransferDTO.getCitizenWithDocumentsTransferInfoDTO().getEmail(), IDOPERATOR, OPERATORNAME);
        rabbitPublishMessage.sendMessageToQueue(REGISTERCITIZENQUEUE, citizenRegister.toString());
    }
}
