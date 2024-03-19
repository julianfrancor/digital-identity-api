package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.brokerintermediary.dto.DocumentDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentMessage {
    private String operation;
    private DocumentDto documentDto;

    public DocumentMessage(String operation, DocumentDto documentDto) {
        this.operation = operation;
        this.documentDto = documentDto;
    }
}

