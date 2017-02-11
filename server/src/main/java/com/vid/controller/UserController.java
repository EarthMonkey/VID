package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.model.User;
import com.vid.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Iterator;

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
     * @param name     用户名
     * @return 注册结果，包括
     * 1. 注册成功： MsgInfo{"status":true,"info":"注册成功","object":null}
     * 2. 用户名已存在： MsgInfo{"status":false,"info":"用户名已存在","object":null}
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo register(String id, String password, String name) {
        return userService.register(id, password, name);
    }

    /**
     * 登录
     *
     * @param id       邮箱/手机号/用户名
     * @param password 密码
     * @return 登录结果，包括
     * 1. 登录成功： MsgInfo{"status":true,"info":"登录成功","object":user{...}}
     * 2. 用户名不存在： MsgInfo{"status":false,"info":"用户名不存在","object":null}
     * 3. 密码错误： MsgInfo{"status":false,"info":"密码错误","object":null}
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo login(HttpSession session, String id, String password) {
        MsgInfo msgInfo = userService.login(id, password);

        if (msgInfo.getStatus()) {
            session.setAttribute("user", msgInfo.getObject());
            session.setAttribute("username", ((User) msgInfo.getObject()).getUsername());
        }

        return msgInfo;
    }

    /**
     * 判断用户是否处于登录状态
     *
     * @return 登录状态，包括
     * 1. 未登录： MsgInfo{"status":false,"info":"未登录","object":null}
     * 2. 已登录： MsgInfo{"status":true,"info":"已登录","object":user{...}}
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo isLogin(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new MsgInfo(false, "未登录");
        } else {
            return new MsgInfo(true, "已登录", session.getAttribute("user"));
        }
    }

    /**
     * 登出
     *
     * @return 登出结果， 包括
     * 1. 登出成功： MsgInfo{"status":true,"info":"登出成功","object":null}
     * 2. 用户未登录 MsgInfo{"status":false,"info":"用户未登录","object":null}
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo logout(HttpSession session) {
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");

            return new MsgInfo(true, "登出成功");
        } else {
            return new MsgInfo(false, "用户未登录");
        }
    }

    /**
     * 找回密码，发送验证邮件
     *
     * @param id 用户名/邮箱/手机号
     * @return 邮件发送结果， 包括
     * 1. 邮件发送成功： MsgInfo{"status":true,"info":"邮件发送成功","object":null}
     * 2. 邮箱未注册： MsgInfo{"status":false,"info":"用户名不存在","object":null}
     */
    @RequestMapping(value = "/findPass", method = RequestMethod.POST)
    @ResponseBody
    public MsgInfo findPass(HttpSession session, String id) {
        return userService.findPass(id);
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
