package com.vid.utils.qiniu;

import com.qiniu.util.Auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by song on 17-4-20.
 * <p>
 * 七牛上传凭证相关
 */
public class AuthHelper {

    private static final String accessKey;

    private static final String secretKey;

    private static final String bucket;

    static {
        Properties properties = new Properties();

        try (InputStream inputStream = AuthHelper.class.getClassLoader().getResourceAsStream("qiniu.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        accessKey = properties.getProperty("AK");
        secretKey = properties.getProperty("SK");
        bucket = properties.getProperty("bucket");
    }

    private AuthHelper() {
        /*do nothing*/
    }

    /**
     * 获取普通上传的凭证
     */
    public static String getToken() {

        Auth auth = Auth.create(accessKey, secretKey);

        return auth.uploadToken(bucket);
    }

    /**
     * 获取覆盖上传的凭证
     *
     * @param fileName 文件名
     */
    public static String getToken(String fileName) {
        Auth auth = Auth.create(accessKey, secretKey);

        return auth.uploadToken(bucket, fileName);
    }
}
