package com.ecostore.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class MailUtils {
    public static final String USERNAME = "gnass993@gmail.com";
    public static final String PASSWORD = "psjw memc ekpn nkju";
    public static final String PORT = "587";
    public static final String HOST = "smtp.gmail.com";
    public static final String STORENAME = "Eco Store";

    private static Session getMailSession(String username, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    public static boolean sendMail(String to, String subject, String message) {
        try {
            MimeMessage m = new MimeMessage(getMailSession(USERNAME, PASSWORD));
            m.setFrom(new InternetAddress(USERNAME, STORENAME));
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            m.setSubject(subject, StandardCharsets.UTF_8.toString());
            m.setText(message, StandardCharsets.UTF_8.toString());
            Transport.send(m);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
