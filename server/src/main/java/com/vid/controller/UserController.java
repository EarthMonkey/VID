package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
public class UserController {

    @Resource
    UserService userService;

    /**
     * 注册
     *
     * @param id       邮箱/手机号
     * @param password 密码
     * @param name     姓名
     * @return 注册结果
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo register(String id, String password, String name) {
        return null;
    }

    /**
     * 登录
     *
     * @param id       邮箱/手机号
     * @param password 密码
     * @return 登录结果
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo login(HttpSession session, String id, String password) {
        return new MsgInfo(true, "登录成功!!!");
    }

    /**
     * 判断用户是否处于登录状态
     *
     * @return 登录状态
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo isLogin(HttpSession session) {
        return null;
    }

    /**
     * 登出
     *
     * @return 登出结果
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo logout(HttpSession session) {
        return null;
    }

    /**
     * 找回密码，发送验证邮件
     *
     * @param id 邮箱/手机号
     * @return 邮件发送结果
     */
    @RequestMapping(value = "/findPass", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo findPass(HttpSession session, String id) {
        return null;
    }

    /**
     * 验证邮件
     *
     * @param id 邮箱/手机号
     * @return 验证结果
     */
    @RequestMapping(value = "/verifyMail", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo verifyMail(String id) {
        return null;
    }

    /**
     * 充值密码
     *
     * @param password 新密码
     * @return
     */
    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo resetPass(HttpSession session, String password) {
        return null;
    }
}
