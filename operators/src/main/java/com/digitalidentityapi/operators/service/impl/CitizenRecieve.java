package com.digitalidentityapi.operators.service.impl;

import com.digitalidentityapi.operators.dto.OperationDTO;
import com.digitalidentityapi.operators.dto.RecieveCitizenDTO;
import com.digitalidentityapi.operators.mapper.RegisterCitizenMapper;
import com.digitalidentityapi.operators.service.RecieveCitizenServices;
import com.digitalidentityapi.operators.utils.rabbit.RabbitPublishMessage;
import com.digitalidentityapi.operators.utils.rabbit.SendCallBack;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.digitalidentityapi.operators.constants.Constants.CITIZEN;

@Service
public class CitizenRecieve implements RecieveCitizenServices {
    private final RabbitPublishMessage rabbitPublishMessage;
    private final SendCallBack sendCallBack;

    @Autowired
    public CitizenRecieve(RabbitPublishMessage rabbitPublishMessage, SendCallBack sendCallBack) {
        this.rabbitPublishMessage = rabbitPublishMessage;
        this.sendCallBack = sendCallBack;
    }

    public String convertOperationDTOToJson(OperationDTO operationDto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(operationDto);
    }

    @Override
    public void recieveCitizen(RecieveCitizenDTO citizenRecieveOperator) throws Exception {
        RegisterCitizenMapper registerCitizenMapper = new RegisterCitizenMapper();
        OperationDTO operationDto = registerCitizenMapper.convertToOperationDTO(citizenRecieveOperator, "CREATE");
        String json = convertOperationDTOToJson(operationDto);
        System.out.println(json);
        rabbitPublishMessage.sendMessageToQueue(CITIZEN, json);
        sendCallBack.sendCallBack(citizenRecieveOperator);
    }
}
