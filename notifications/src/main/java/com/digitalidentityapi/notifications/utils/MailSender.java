package com.digitalidentityapi.notifications.utils;


import com.digitalidentityapi.notifications.constants.Constants;

import javax.mail.*;
import java.util.Properties;

public class MailSender {

    public static void SendMail(String messageToSend, String email) {
        Properties props = PropiertiesMail.getPropierties();
        Authenticator autenticador = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.USERNAME, Constants.PASSAPP);
            }
        };
        Session session = Session.getInstance(props, autenticador);
        Message message = BuildMessage.buildMessage(messageToSend, session);
        try {
            Transport.send(message);
            System.out.println(Constants.SUCCESFULLMESSAGESEND);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
