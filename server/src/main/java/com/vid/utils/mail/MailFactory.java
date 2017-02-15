package com.vid.utils.mail;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by song on 17-2-15.
 * <p>
 * 邮件工厂
 */
public class MailFactory {
    private MailFactory() {
        /*do nothing*/
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
        String subject = "VID用户找回密码";

        String content = "亲爱的" + userName + "： \n" +
                "   您好！\n" +
                "   非常感谢您使用VID提供的服务，请点击下方链接重置密码：\n" +
                "   http://vid.com/resetPass?uerID=" + userID + "&random=" + random +
                "\n\n" +
                "温馨提示：\n" +
                "若无法打开链接，请复制链接地址到浏览器中进行打开。\n" +
                "VID账号团队\n" +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        return MailHelper.sendMail(mail, subject, content);
    }
}
