package com.digitalidentityapi.notifications.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SendNotifications {
    private String email;
    private String message;

}

