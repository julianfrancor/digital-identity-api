package com.digitalidentityapi.operators.mapper;

import com.digitalidentityapi.operators.dto.DtoRegisterCitizen;
import com.digitalidentityapi.operators.dto.OperationDTO;
import com.digitalidentityapi.operators.dto.RecieveCitizenDTO;
import com.digitalidentityapi.operators.entity.CitizenRecieveOperator;

public class RegisterCitizenMapper {
    public OperationDTO convertToOperationDTO(RecieveCitizenDTO citizen, String operation) {
        DtoRegisterCitizen citizenDto = new DtoRegisterCitizen();
        citizenDto.setIdentification(citizen.getId().longValue());
        citizenDto.setIdentificationType("CC");
        citizenDto.setFullName(citizen.getName());
        citizenDto.setAddress(citizen.getAddress());
        citizenDto.setEmail(citizen.getEmail());
        citizenDto.setPhone("12025550147");
        citizenDto.setStatus("ALIVE");
        OperationDTO operationDto = new OperationDTO();
        operationDto.setOperation(operation);
        operationDto.setCitizenDto(citizenDto);
        return operationDto;
    }
}
