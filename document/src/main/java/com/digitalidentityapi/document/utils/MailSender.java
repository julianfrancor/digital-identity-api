package com.digitalidentityapi.document.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
        // Crear la sesión de correo
        Session session = Session.getInstance(props, autenticador);
        try {
            // Crear un mensaje de correo electrónico
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(Constants.USERNAME));
            String[] destinatarios = {Constants.CORREOPIPE, Constants.CORREOJULI, Constants.CORREOYULI};
            InternetAddress[] direccionesDestinatarios = new InternetAddress[destinatarios.length];
            for (int i = 0; i < destinatarios.length; i++) {
                direccionesDestinatarios[i] = new InternetAddress(destinatarios[i]);
            }
            //mensaje.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Constants.CORREOPIPE));
            mensaje.setRecipients(Message.RecipientType.TO, direccionesDestinatarios);
            mensaje.setSubject("Notificacion Operacion en Identidad Digital");
            mensaje.setText("Hola, Identidad Digital tiene un mensaje para ti : " + messageToSend);
            Transport.send(mensaje);
            System.out.println("Correo electrónico enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
        }
    }
}
