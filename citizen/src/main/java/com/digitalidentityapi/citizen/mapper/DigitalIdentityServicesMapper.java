package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.DigitalIdentityServicesDto;
import com.digitalidentityapi.citizen.entity.DigitalIdentityServices;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;

public class DigitalIdentityServicesMapper {

    public static DigitalIdentityServicesDto toDto(DigitalIdentityServices service) {
        if (service == null) {
            return null;
        }

        return DigitalIdentityServicesDto.builder()
                .id(service.getId())
                .name(service.getName())
                .isPremium(service.isPremium())
                .createdAt(convertToDate(service.getCreatedAt()))
                .updatedAt(convertToDate(service.getUpdatedAt()))
                .build();
    }

    public static DigitalIdentityServices toEntity(DigitalIdentityServicesDto dto) {
        if (dto == null) {
            return null;
        }

        DigitalIdentityServices service = new DigitalIdentityServices();
        service.setName(dto.getName());
        service.setPremium(dto.getIsPremium());

        return service;
    }
}
