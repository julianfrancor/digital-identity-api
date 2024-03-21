package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.brokerintermediary.dto.TransferRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferMessage {
    private Long citizenIdentification;
    private String citizenEmail;
    private String destinationOperatorId;

    public TransferMessage(TransferRequestDto transferRequestDto) {
        this.citizenIdentification = transferRequestDto.getCitizenIdentification();
        this.citizenEmail = transferRequestDto.getCitizenEmail();
        this.destinationOperatorId = transferRequestDto.getDestinationOperatorId();
    }
}

