package com.digitalidentityapi.brokerintermediary.Message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationMessage {
    private String email;
    private String message;

    public NotificationMessage(String email, String message) {
        this.email = email;
        this.message = message;
    }
}
