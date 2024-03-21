package com.digitalidentityapi.brokerintermediary.Message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCitizenMessage {
    private String id;
    private String name;
    private String address;
    private String email;

    public RegisterCitizenMessage(String id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
