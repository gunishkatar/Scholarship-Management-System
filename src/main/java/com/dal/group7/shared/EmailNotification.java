package com.dal.group7.shared;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotification {
    private static final String sentByEmail = "kvskdomains@gmail.com";
    private static final String mailPwd = "Qwertvbnm@290";

    private static Session createUserSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.enable", false);

        Session userSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sentByEmail, mailPwd);
            }
        });
        return userSession;
    }

    private static void constructMailMsg(MimeMessage msg, String recipient, String heading, String mailBody)
            throws MessagingException {
        msg.setContent(mailBody, "text/html; charset=utf-8");
        msg.setFrom(new InternetAddress(sentByEmail));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        msg.setSubject(heading);
    }

    public static void sendMailMsg(String recipient, String heading, String mailBody) throws MessagingException {
        System.out.println("Sending a Mail to email :  " + recipient);
        Session createSession = createUserSession();
        MimeMessage msg = new MimeMessage(createSession);
        constructMailMsg(msg, recipient, heading, mailBody);
        Transport.send(msg);
        System.out.println("Mail sent successfully");
    }
}
