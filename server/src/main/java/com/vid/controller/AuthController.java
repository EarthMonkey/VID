package com.vid.controller;

import com.qiniu.util.Auth;
import com.vid.config.MsgInfo;
import com.vid.utils.qiniu.AuthHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by song on 17-4-20.
 * <p>
 * 七牛上传凭证相关
 *
 * 普通上传：
 * url： /auth/token
 * 参数：无
 *
 * 覆盖上传：
 * url： /auth/token/cover
 * 参数：fileName 要覆盖的文件名
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * 获取普通上传凭证
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo getUploadToken() {
        return new MsgInfo(true, "", AuthHelper.getToken());
    }

    /**
     * 获取覆盖上传凭证
     *
     * @param fileName 文件名
     */
    @RequestMapping(value = "/token/cover", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo getUploadToken(@RequestParam String fileName) {
        return new MsgInfo(true, "", AuthHelper.getToken(fileName));
    }
}
