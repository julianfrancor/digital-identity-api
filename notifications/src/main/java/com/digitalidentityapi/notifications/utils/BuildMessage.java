package com.digitalidentityapi.notifications.utils;

import com.digitalidentityapi.notifications.constants.Constants;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BuildMessage {
    public static Message buildMessage(String messageToSend, Session session) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(Constants.USERNAME));
            //String[] destinatarios = {Constants.CORREOPIPE, Constants.CORREOJULI, Constants.CORREOYULI};
            String[] destinatarios = {Constants.CORREOPIPE};
            InternetAddress[] direccionesDestinatarios = new InternetAddress[destinatarios.length];
            for (int i = 0; i < destinatarios.length; i++) {
                direccionesDestinatarios[i] = new InternetAddress(destinatarios[i]);
            }
            message.setRecipients(Message.RecipientType.TO, direccionesDestinatarios);
            message.setSubject(Constants.SUBJECT);
            message.setText(Constants.MESSAGE + messageToSend);

        } catch (
                MessagingException e) {
            e.printStackTrace();
            System.err.println(Constants.ERRORSENDMESSAGE + e.getMessage());
        }
        return message;
    }
}