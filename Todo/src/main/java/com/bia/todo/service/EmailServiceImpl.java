package com.bia.todo.service;

/**
 *
 * @author intesar
 */
import java.security.Security;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    protected Session session;
    protected InternetAddress[] bcc;

    @Override
    @Async
    public void sendEmail(String toAddress, String subject, String body) {
        String[] to = {toAddress};
        sendSSMessage(to, subject, body);
    }

    @Override
    @Async
    public void sendEmail(String[] to, String subject, String body) {
        sendSSMessage(to, subject, body);
    }

    

    @Override
    public Session createSession() {

        if (session != null) {
            return session;
        }
        boolean debug = false;

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SEND_FROM_USERNAME, SEND_FROM_PASSWORD); // todo add password before deploy
                    }
                });

        session.setDebug(debug);
        return session;
    }

    private InternetAddress[] getBCC() throws AddressException {
        if (bcc != null) {
            return bcc;
        }
        bcc = new InternetAddress[1];
        bcc[0] = new InternetAddress("mdshannan@gmail.com");
        //bcc[1] = new InternetAddress("atefahmed@gmail.com");
        return bcc;
    }

    /**
     * 
     * @param recipients
     * @param subject
     * @param message
     * @param from
     * @throws MessagingException
     */
    private void sendSSMessage(String recipients[], String subject,
            String message) {

        try {
            if (recipients == null || recipients.length == 0) {
                return;
            }

            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                if (recipients[i] != null && recipients[i].length() > 0) {

                    addressTo[i] = new InternetAddress(recipients[i]);

                }
            }
            send(addressTo, subject, message);
            
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            throw new RuntimeException("Error sending email, please check to and from emails are correct!");
        }
    }

    private void send(InternetAddress[] addressTo, String subject, String message) throws AddressException, MessagingException {
        Message msg = new MimeMessage(createSession());
        InternetAddress addressFrom = new InternetAddress(SEND_FROM_USERNAME);
        msg.setFrom(addressFrom);
        msg.setRecipients(Message.RecipientType.TO, addressTo);
        InternetAddress[] bcc1 = getBCC();
        msg.setRecipients(Message.RecipientType.BCC, bcc1);
        // Setting the Subject and Content Type
        msg.setSubject(subject);
        //String message = comment;
        msg.setContent(message, EMAIL_CONTENT_TYPE);

        Transport.send(msg);
    }
    protected static Logger logger = Logger.getLogger(EmailServiceImpl.class);
}
