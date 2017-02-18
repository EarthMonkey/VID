package com.vid.utils.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        InputStreamReader reader = new InputStreamReader(MailFactoryTest.class
                .getClassLoader().getResourceAsStream("mailContent.properties"), "UTF8");

        properties.load(reader);

        String test = properties.getProperty("verifyMail.content");
        System.out.println(test);

        System.out.println(String.format(test, "bedisdover", 141, "hello ", new Date(), new Date()));
    }
}