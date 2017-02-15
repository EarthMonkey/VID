package com.vid.utils.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by song on 17-2-15.
 * <p>
 * 邮件相关
 */
public class MailHelper {

    private static String userName;

    private static String password;

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            InputStream inputStream = MailHelper.class.getClassLoader().getResourceAsStream("mail.properties");

            properties.load(inputStream);

            userName = properties.getProperty("mail.smtp.user");
            password = properties.getProperty("mail.smtp.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MailHelper() {
        /*do nothing*/
    }

    /**
     * 发送邮件
     *
     * @param receiver 收件人邮箱
     * @param content  邮件内容
     * @return 发送成功返回true，否则返回false
     */
    public static boolean sendMail(String receiver, String subject, String content) {
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session mailSession = Session.getInstance(properties, authenticator);

        MimeMessage message = new MimeMessage(mailSession);
        try {
            // 发件人
            message.setFrom(new InternetAddress(userName));
            // 收件人邮箱
            message.setRecipients(Message.RecipientType.TO, receiver);
            // 邮件主题
            message.setSubject(subject);
            // 邮件内容
            message.setText(content);
            message.setSentDate(new Date());
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
