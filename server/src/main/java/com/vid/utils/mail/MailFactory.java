package com.vid.utils.mail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

/**
 * Created by song on 17-2-15.
 * <p>
 * 邮件工厂
 */
public class MailFactory {

    /**
     * 激活账户邮件标题
     */
    private static String activateSubject;

    /**
     * 激活账户邮件内容
     */
    private static String activateContent;

    /**
     * 找回密码邮件标题
     */
    private static String findPassSubject;

    /***
     * 找回密码邮件内容
     */
    private static String findPassContent;

    /**
     * 验证邮件标题
     */
    private static String verifyMailSubject;

    /**
     * 验证邮件内容
     */
    private static String verifyMailContent;

    static {
        try {
            InputStreamReader reader = new InputStreamReader(
                    MailFactory.class.getClassLoader().getResourceAsStream("mailContent.properties"),
                    "UTF8"
            );

            Properties properties = new Properties();
            properties.load(reader);

            activateSubject = properties.getProperty("activate.subject");
            activateContent = properties.getProperty("activate.content");

            findPassSubject = properties.getProperty("findPass.subject");
            findPassContent = properties.getProperty("findPass.content");

            verifyMailSubject = properties.getProperty("verifyMail.subject");
            verifyMailContent = properties.getProperty("verifyMail.content");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MailFactory() {
        /*do nothing*/
    }

    /**
     * 发送激活账户邮件
     *
     * @param mail   收件人邮箱
     * @param random 随机数，用于链接识别
     * @return 发送成功返回true，否则返回false
     */
    public static boolean activateAccount(final String mail, final String random) {
        Date date = new Date();
        String content = String.format(activateContent, mail, random, date, date);

        return MailHelper.sendHtmlMail(mail, activateSubject, content);
    }

    /**
     * 发送找回密码邮件
     *
     * @param mail     收件人邮箱
     * @param userID   用户ID，用于链接
     * @param userName 用户名，用于邮件正文
     * @param random   随机数，用于链接识别
     * @return 发送成功返回true，否则返回false
     */
    public static boolean findPass(String mail, int userID, String userName, String random) {
        Date date = new Date();

        String content = String.format(findPassContent, userName, userID, random, date, date);

        return MailHelper.sendHtmlMail(mail, findPassSubject, content);
    }

    /**
     * 发送验证邮件，用于修改邮箱地址
     *
     * @param mail     收件人地址
     * @param userID   userID，用于链接
     * @param userName 用户名，用于邮件正文
     * @param random   随机数，用于链接识别
     * @return 发送成功返回true，否则返回false
     */
    public static boolean verifyMail(String mail, int userID, String userName, String random) {
        Date date = new Date();

        String content = String.format(verifyMailContent, userName, userID, random, date, date);

        return MailHelper.sendHtmlMail(mail, findPassSubject, content);
    }

    public static void main(String[] args) {
        System.out.println(activateAccount("141250111@smail.nju.edu.cn", "123123"));
        System.out.println(verifyMail("141250111@smail.nju.edu.cn", 141, "bedisdover", "123"));
    }
}
