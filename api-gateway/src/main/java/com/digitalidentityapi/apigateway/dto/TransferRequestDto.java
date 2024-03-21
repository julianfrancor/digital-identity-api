package com.digitalidentityapi.apigateway.dto;


public class TransferRequestDto {
    private Long citizenIdentification;
    private String citizenEmail;
    private int destinationOperatorId;

    public TransferRequestDto(Long citizenIdentification, String citizenEmail, int destinationOperatorId) {
        this.citizenIdentification = citizenIdentification;
        this.citizenEmail = citizenEmail;
        this.destinationOperatorId = destinationOperatorId;
    }
}
