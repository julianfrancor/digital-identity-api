package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.CitizenDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.enums.Status;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;

public class CitizenMapper {
    public static CitizenDto toDto(Citizen citizen) {
        if (citizen == null) {
            throw new IllegalArgumentException("Cannot map a null Citizen to CitizenDto.");
        }
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
        citizenDto.setCreatedAt(convertToDate(citizen.getCreatedAt()));
        citizenDto.setUpdatedAt(convertToDate(citizen.getUpdatedAt()));

        return citizenDto;
    }


    public static Citizen toEntity(CitizenDto citizenDto) {
        Citizen citizen = new Citizen();
        citizen.setIdentification(citizenDto.getIdentification());
        citizen.setIdentificationType(citizenDto.getIdentificationType());
        citizen.setFirstName(citizenDto.getFirstName());
        citizen.setSecondName(citizenDto.getSecondName());
        citizen.setLastName(citizenDto.getLastName());
        citizen.setSecondLastName(citizenDto.getSecondLastName());
        citizen.setAddress(citizenDto.getAddress());
        citizen.setEmail(citizenDto.getEmail());
        citizen.setPhone(citizenDto.getPhone());
        citizen.setStatus(Status.valueOf(citizenDto.getStatus()));
        return citizen;
    }
}
