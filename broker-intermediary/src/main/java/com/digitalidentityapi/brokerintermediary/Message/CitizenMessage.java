package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.citizen.dto.CitizenDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenMessage {
    private String operation;
    private CitizenDto citizenDto;
}

