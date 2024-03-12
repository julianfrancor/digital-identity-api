package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.TransfersDto;
import com.digitalidentityapi.citizen.entity.Transfer;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;


public class TransfersMapper {

    public static TransfersDto toDto(Transfer transfer, String citizenEmail) {
        if (transfer == null){
            throw new IllegalArgumentException("Cannot map a null Transfer to TransfersDto.");
        }
        TransfersDto transfersDto =  new TransfersDto();

        transfersDto.setId(transfer.getId());
        transfersDto.setCitizenId(String.valueOf(transfer.getCitizenId()));
        transfersDto.setOperatorId(String.valueOf(transfer.getOperatorId()));
        transfersDto.setCitizenEmail(citizenEmail);
        transfersDto.setCreatedAt(convertToDate(transfer.getCreatedAt()));
        transfersDto.setUpdatedAt(convertToDate(transfer.getCreatedAt()));

        return transfersDto;
    }

    public static Transfer toEntity(TransfersDto transferDto, Transfer transfer) {
        if (transfer == null) {
            transfer = new Transfer();
        }

        transfer.setCitizenId(Integer.parseInt(transferDto.getCitizenId()));
        transfer.setOperatorId(Integer.parseInt(transferDto.getOperatorId()));
        transfer.setTransferDate(transferDto.getTransferDate());
        return transfer;
    }
}