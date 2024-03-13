package com.digitalidentityapi.notifications.service.impl;

import com.digitalidentityapi.notifications.service.SendNotificationsServices;
import com.digitalidentityapi.notifications.utils.MailSender;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SendNotification implements SendNotificationsServices {

    @Override
    public void sendNotifications(String message) {
        JSONObject json = new JSONObject(message);
        System.out.println("Received: " + message);
        MailSender.SendMail(json.getString("message").replace("\"", " "), json.getString("email"));
    }
}

