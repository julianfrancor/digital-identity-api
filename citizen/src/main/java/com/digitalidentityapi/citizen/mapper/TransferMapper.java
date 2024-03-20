package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.TransferDto;
import com.digitalidentityapi.citizen.entity.Transfer;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;


public class TransferMapper {

    public static TransferDto toDto(Transfer transfer, String citizenEmail) {
        if (transfer == null){
            throw new IllegalArgumentException("Cannot map a null Transfer to TransferDto.");
        }
        TransferDto transferDto =  new TransferDto();

        transferDto.setId(transfer.getId());
        transferDto.setCitizenId(transfer.getCitizenId());
        transferDto.setOperatorId(String.valueOf(transfer.getOperatorId()));
        transferDto.setCitizenEmail(citizenEmail);
        transferDto.setCreatedAt(convertToDate(transfer.getCreatedAt()));
        transferDto.setUpdatedAt(convertToDate(transfer.getCreatedAt()));

        return transferDto;
    }

    public static Transfer toEntity(TransferDto transferDto, Transfer transfer) {
        if (transfer == null) {
            transfer = new Transfer();
        }

        transfer.setCitizenId(transferDto.getCitizenId());
        transfer.setOperatorId(Integer.parseInt(transferDto.getOperatorId()));
        transfer.setTransferDate(transferDto.getTransferDate());
        return transfer;
    }
}