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

    private static Authenticator authenticator;

    static {
        try {
            properties = new Properties();
            InputStream inputStream = MailHelper.class.getClassLoader().getResourceAsStream("mail.properties");

            properties.load(inputStream);

            userName = properties.getProperty("mail.smtp.user");
            password = properties.getProperty("mail.smtp.password");

            authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MailHelper() {
        /*do nothing*/
    }

    /**
     * 发送纯文本邮件
     *
     * @param receiver 收件人邮箱
     * @param content  邮件内容
     * @return 发送成功返回true，否则返回false
     */
    public static boolean sendTextMail(String receiver, String subject, String content) {
        return sendMail("text/plain;charset=UTF8", receiver, subject, content);
    }

    /**
     * 发送html邮件
     *
     * @param receiver 收件人邮箱
     * @param content  邮件内容
     * @return 发送成功返回true，否则返回false
     */
    public static boolean sendHtmlMail(String receiver, String subject, String content) {
        return sendMail("text/html;charset=UTF8", receiver, subject, content);
    }

    /**
     * 发送邮件
     *
     * @param contentType 文本类型（纯文本/html）
     * @param receiver    收件人邮箱
     * @param content     邮件内容
     * @return 发送成功返回true，否则返回false
     */
    private static boolean sendMail(String contentType, String receiver, String subject, String content) {
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
            message.setContent(content, contentType);
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
