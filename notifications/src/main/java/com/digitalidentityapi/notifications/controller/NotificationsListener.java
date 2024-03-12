package com.digitalidentityapi.notifications.controller;

import com.digitalidentityapi.notifications.service.SendNotificationsServices;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationsListener {
    @Autowired
    SendNotificationsServices sendNotificationsServices;

    @RabbitListener(queues = "notificationQueue")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido desde la cola notificationQueue: " + message);
        sendNotificationsServices.sendNotifications(message);
    }
}
