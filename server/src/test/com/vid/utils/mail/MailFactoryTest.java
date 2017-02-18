package com.vid.utils.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by song on 17-2-18.
 */
public class MailFactoryTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        InputStream inputStream = MailFactoryTest.class
                .getClassLoader().getResourceAsStream("mailContent.properties");

        properties.load(inputStream);

        String test = properties.getProperty("mail.content.findPass");

        System.out.println(String.format(test, "bedisdover", 141, "hello ", new Date(), new Date()));
    }
}