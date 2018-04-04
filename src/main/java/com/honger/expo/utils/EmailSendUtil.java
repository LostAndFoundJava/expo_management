package com.honger.expo.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailSendUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSendUtil.class);


    public static void sendEmailByAli(String subject,String content){
        Properties prop=new Properties();
        prop.put("mail.host","smtp.hongerzl.com" );
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // 使用JSSE的SSL
        prop.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
        prop.put("mail.smtp.socketFactory.port","465");
        //使用java发送邮件5步骤
        //1.创建sesssion
        Session session=Session.getInstance(prop);
        //开启session的调试模式，可以查看当前邮件发送状态
        session.setDebug(true);


        try{
            //2.通过session获取Transport对象（发送邮件的核心API）
            Transport ts = session.getTransport();
            if(null != ts){
                //3.通过邮件用户名密码链接，阿里云默认是开启个人邮箱pop3、smtp协议的，所以无需在阿里云邮箱里设置
                ts.connect("postmaster@hongerzl.com", "123qwe456ASD");
                //4.创建邮件
                Message msg = createSimpleMail(session,subject,content);
                //5.发送电子邮件
                ts.sendMessage(msg, msg.getAllRecipients());
            }

        }catch (Exception e){
            LOGGER.warn("发送邮件失败",e);
        }
    }

    private static MimeMessage createSimpleMail(Session session,String subject,String content) throws AddressException,MessagingException{
        //创建邮件对象
        MimeMessage mm=new MimeMessage(session);
        //设置发件人
        mm.setFrom(new InternetAddress("postmaster@hongerzl.com"));
        //设置收件人
        mm.setRecipient(Message.RecipientType.TO, new InternetAddress(""));
        //设置抄送人
//        mm.setRecipient(Message.RecipientType.CC, new InternetAddress(""));

        mm.setSubject(subject);
        mm.setContent(content, "text/html;charset=gbk");

        return mm;

    }

    public static void main(String[] args) {
        sendEmailByAli("邮件测试","这是发送邮件的测试内容");
    }
}
