package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.OfferedSolutionDto;
import com.digitalidentityapi.citizen.entity.OfferedSolution;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;

public class OfferedSolutionMapper {

    public static OfferedSolutionDto toDto(OfferedSolution entity) {
        if (entity == null) {
            return null;
        }

        OfferedSolutionDto dto = new OfferedSolutionDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setExternalCompanyId(entity.getExternalCompanyId());
        dto.setNit(entity.getNit());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setCreatedAt(convertToDate(entity.getCreatedAt()));
        dto.setUpdatedAt(convertToDate(entity.getUpdatedAt()));

        return dto;
    }

    public static OfferedSolution toEntity(OfferedSolutionDto dto) {
        if (dto == null) {
            return null;
        }
        OfferedSolution entity = new OfferedSolution();

        entity.setName(dto.getName());
        entity.setExternalCompanyId(dto.getExternalCompanyId());
        entity.setNit(dto.getNit());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        return entity;
    }

    public static void updateEntity(OfferedSolution entity, OfferedSolutionDto dto) {
        if (entity != null && dto != null) {
            entity.setName(dto.getName());
            entity.setExternalCompanyId(dto.getExternalCompanyId());
            entity.setNit(dto.getNit());
            entity.setAddress(dto.getAddress());
            entity.setEmail(dto.getEmail());
            entity.setPhone(dto.getPhone());
        }
    }
}
