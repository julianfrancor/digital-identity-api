package com.digitalidentityapi.citizen.dto;

import com.digitalidentityapi.citizen.Message.TransferRequestMessage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TransferRequestDto {
    private String citizenIdentification;
    private String citizenEmail;
    private String destinationOperatorId;

    public TransferRequestDto(TransferRequestMessage transferRequestMessage) {
        this.citizenIdentification = transferRequestMessage.getCitizenIdentification();
        this.citizenEmail = transferRequestMessage.getCitizenEmail();
        this.destinationOperatorId = transferRequestMessage.getDestinationOperatorId();
    }
}
