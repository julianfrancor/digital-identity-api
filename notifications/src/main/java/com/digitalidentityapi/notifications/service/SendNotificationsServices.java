package com.digitalidentityapi.notifications.service;

import org.springframework.stereotype.Service;

@Service
public interface SendNotificationsServices {
    void sendNotifications(String message);
}
