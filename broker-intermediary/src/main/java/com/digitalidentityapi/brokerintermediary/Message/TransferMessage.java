package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.brokerintermediary.dto.DocumentDto;
import com.digitalidentityapi.brokerintermediary.dto.TransferDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferMessage {
    private String operation;
    private TransferDto transferDto;

    public TransferMessage(String operation, TransferDto transferDto) {
        this.operation = operation;
        this.transferDto = transferDto;
    }
}

