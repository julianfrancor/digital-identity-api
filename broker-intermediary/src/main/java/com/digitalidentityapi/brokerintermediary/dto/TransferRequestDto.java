package com.digitalidentityapi.brokerintermediary.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransferRequestDto {
    private String citizenIdentification;
    private String citizenEmail;
    private String destinationOperatorId;

    public TransferRequestDto(String citizenIdentification, String citizenEmail, String destinationOperatorId) {
        this.citizenIdentification = citizenIdentification;
        this.citizenEmail = citizenEmail;
        this.destinationOperatorId = destinationOperatorId;
    }
}
