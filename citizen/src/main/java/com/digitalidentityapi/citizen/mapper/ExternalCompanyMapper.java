package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.ExternalCompanyDto;
import com.digitalidentityapi.citizen.entity.ExternalCompany;

public class ExternalCompanyMapper {

    public static ExternalCompany toEntity(ExternalCompanyDto dto) {
        ExternalCompany entity = new ExternalCompany();
        entity.setId(dto.getId());
        entity.setExternalCompanyId(dto.getExternalCompanyId());
        entity.setDigitalIdentityServiceId(dto.getDigitalIdentityServiceId());
        entity.setAffiliationDate(dto.getAffiliationDate());
        entity.setDischargeDate(dto.getDischargeDate());
        return entity;
    }

    public static ExternalCompanyDto toDto(ExternalCompany entity) {
        return ExternalCompanyDto.builder()
                .id(entity.getId())
                .externalCompanyId(entity.getExternalCompanyId())
                .digitalIdentityServiceId(entity.getDigitalIdentityServiceId())
                .affiliationDate(entity.getAffiliationDate())
                .dischargeDate(entity.getDischargeDate())
                .build();
    }

    public static void updateEntityFromDto(ExternalCompanyDto dto, ExternalCompany entity) {
        if (dto.getExternalCompanyId() != 0) { // Assumes 0 is default or invalid value
            entity.setExternalCompanyId(dto.getExternalCompanyId());
        }
        if (dto.getDigitalIdentityServiceId() != 0) {
            entity.setDigitalIdentityServiceId(dto.getDigitalIdentityServiceId());
        }
        if (dto.getAffiliationDate() != null) {
            entity.setAffiliationDate(dto.getAffiliationDate());
        }
        if (dto.getDischargeDate() != null) {
            entity.setDischargeDate(dto.getDischargeDate());
        }
    }
}
