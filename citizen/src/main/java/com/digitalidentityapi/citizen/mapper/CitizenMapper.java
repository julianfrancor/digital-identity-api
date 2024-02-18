package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.CitizenDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.enums.IdentificationType;
import com.digitalidentityapi.citizen.enums.Status;

import java.util.Date;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;
import static com.digitalidentityapi.citizen.utils.Utils.convertToLocalDateTime;

public class CitizenMapper {
    public static CitizenDto mapToCitizenDto(Citizen citizen, CitizenDto citizenDto) {
        citizenDto.setFirstName(citizen.getFirstName());
        citizenDto.setEmail(citizen.getEmail());
        citizenDto.setPhone(citizen.getPhone());
        return citizenDto;
    }

    public static CitizenDto mapToCitizenDto(Citizen citizen) {
        CitizenDto citizenDto = new CitizenDto();

        citizenDto.setId(citizen.getId());
        citizenDto.setIdentification(citizen.getIdentification());
        citizenDto.setIdentificationType(String.valueOf(citizen.getIdentificationType()));
        citizenDto.setFirstName(citizen.getFirstName());
        citizenDto.setSecondName(citizen.getSecondName());
        citizenDto.setLastName(citizen.getLastName());
        citizenDto.setSecondLastName(citizen.getSecondLastName());
        citizenDto.setAddress(citizen.getAddress());
        citizenDto.setEmail(citizen.getEmail());
        citizenDto.setPhone(citizen.getPhone());
        citizenDto.setStatus(String.valueOf(citizen.getStatus()));

        if (citizen.getCreatedAt() != null) {
            citizenDto.setCreatedAt(convertToDate(citizen.getCreatedAt()));
        }
        if (citizen.getUpdatedAt() != null) {
            citizenDto.setUpdatedAt(convertToDate(citizen.getUpdatedAt()));
        }

        return citizenDto;
    }


    public static Citizen mapToCitizen(CitizenDto citizenDto, Citizen citizen) {
        if (citizenDto.getId() != null) {
            citizen.setId(citizenDto.getId());
        }
        citizen.setIdentification(citizenDto.getIdentification());
        citizen.setIdentificationType(IdentificationType.valueOf(citizenDto.getIdentificationType()));
        citizen.setFirstName(citizenDto.getFirstName());
        citizen.setSecondName(citizenDto.getSecondName());
        citizen.setLastName(citizenDto.getLastName());
        citizen.setSecondLastName(citizenDto.getSecondLastName());
        citizen.setAddress(citizenDto.getAddress());
        citizen.setEmail(citizenDto.getEmail());
        citizen.setPhone(citizenDto.getPhone());
        citizen.setStatus(Status.valueOf(citizenDto.getStatus()));
        if (citizen.getCreatedAt() != null) {
            citizen.setCreatedAt(convertToLocalDateTime(citizenDto.getCreatedAt()));
        }
        if (citizen.getUpdatedAt() != null) {
            citizen.setUpdatedAt(convertToLocalDateTime(citizenDto.getUpdatedAt()));
        }

        return citizen;
    }

}
