package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.brokerintermediary.dto.TransferRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferMessage {
    private String operation;
    private TransferRequestDto transferRequestDto;

    public TransferMessage(String operation, TransferRequestDto transferRequestDto) {
        this.operation = operation;
        this.transferRequestDto = transferRequestDto;
    }
}

