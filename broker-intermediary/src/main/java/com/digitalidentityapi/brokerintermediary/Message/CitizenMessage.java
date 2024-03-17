package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.brokerintermediary.dto.CitizenDto;
import com.digitalidentityapi.brokerintermediary.service.IBrokerIntermediaryService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenMessage {
    private String operation;
    private CitizenDto citizenDto;

    public CitizenMessage(String operation, CitizenDto citizenDto) {
        this.operation = operation;
        this.citizenDto = citizenDto;
    }
}

