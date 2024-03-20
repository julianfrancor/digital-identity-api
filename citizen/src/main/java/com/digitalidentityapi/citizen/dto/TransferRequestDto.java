package com.digitalidentityapi.citizen.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
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
