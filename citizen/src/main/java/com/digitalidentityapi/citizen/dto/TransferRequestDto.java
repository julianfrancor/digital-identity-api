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
    private String destinationOperatorId;

    public TransferRequestDto(Long citizenIdentification, String citizenEmail, String destinationOperatorId) {
        this.citizenIdentification = citizenIdentification;
        this.citizenEmail = citizenEmail;
        this.destinationOperatorId = destinationOperatorId;
    }
}
