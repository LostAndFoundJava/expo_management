package com.honger.expo.mail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by chenjian on 2018/4/16.
 */
public class SendMailTest {
    public static void main(String[] args) throws Exception {
        String fromEmail = "postmaster@hongerzl.com";
        String toEmail = "779187545@qq.com";
        String emailName = "postmaster@hongerzl.com";
        String emailPassword = "123qwe456ASD";
        String title = "ni hao";
        String content = "test";
        sendMail(fromEmail,toEmail,emailName,emailPassword,title,content);
    }

    public static void sendMail(String fromEmail, String toEmail, String emailName,
                                String emailPassword, String title,
                                String centent) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.host", "smtp.mxhichina.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect(emailName, emailPassword);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        message.setSubject(title);
        message.setContent(centent, "text/html;charset=utf-8");
        ts.sendMessage(message, message.getAllRecipients());
    }
}

