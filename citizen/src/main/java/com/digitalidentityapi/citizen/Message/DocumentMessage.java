package com.digitalidentityapi.citizen.Message;

import com.digitalidentityapi.citizen.dto.DocumentDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentMessage {
    private String operation;
    private DocumentDto citizenDto;
}
