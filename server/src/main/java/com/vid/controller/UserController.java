package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.model.AllContacts;
import com.vid.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Controller
@RequestMapping(method = RequestMethod.POST)
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     *
     * @param email    邮箱
     * @param phoneNum 手机号，可选
     * @param password 密码
     * @param name     用户名
     * @return 注册结果，包括
     * 1. 注册成功： MsgInfo{"status":true,"info":"邮件发送成功","object":null}
     * 2. 用户名已存在： MsgInfo{"status":false,"info":"用户名已存在","object":null}
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    public MsgInfo register(@RequestParam String email, String phoneNum, @RequestParam String password, @RequestParam String name) {
        return userService.register(email, phoneNum, password, name);
    }

//    public MsgInfo activateAccount(@RequestMapping String email, String )

    /**
     * 登录
     *
     * @param id       邮箱/手机号
     * @param password 密码
     * @return 登录结果，包括
     * 1. 登录成功： MsgInfo{"status":true,"info":"登录成功","object": @code AllContacts}
     * 2. 用户名不存在： MsgInfo{"status":false,"info":"用户名不存在","object":null}
     * 3. 密码错误： MsgInfo{"status":false,"info":"密码错误","object":null}
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public MsgInfo login(HttpSession session, @RequestParam String id, @RequestParam String password) {
        MsgInfo msgInfo = userService.login(id, password);

        if (msgInfo.getStatus()) {
            session.setAttribute("userID", ((AllContacts) msgInfo.getObject()).getUserID());
        }

        return msgInfo;
    }

    /**
     * 判断用户是否处于登录状态
     *
     * @return 登录状态，包括
     * 1. 未登录： MsgInfo{"status":false,"info":"未登录","object":null}
     * 2. 已登录： MsgInfo{"status":true,"info":"已登录","object":null}
     */
    @RequestMapping(value = "/isLogin")
    @ResponseBody
    public MsgInfo isLogin(HttpSession session) {
        if (session.getAttribute("userID") == null) {
            return new MsgInfo(false, "未登录");
        } else {
            return new MsgInfo(true, "已登录");
        }
    }

    /**
     * 登出
     *
     * @return 登出结果， 包括
     * 1. 登出成功： MsgInfo{"status":true,"info":"登出成功","object":null}
     * 2. 用户未登录 MsgInfo{"status":false,"info":"用户未登录","object":null}
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public MsgInfo logout(HttpSession session) {
        if (session.getAttribute("userID") != null) {
            session.removeAttribute("userID");

            return new MsgInfo(true, "登出成功");
        } else {
            return new MsgInfo(false, "用户未登录");
        }
    }

    /**
     * 找回密码，发送验证邮件
     *
     * @param id 邮箱/手机号
     * @return 邮件发送结果， 包括
     * 1. 邮件发送成功： MsgInfo{"status":true,"info":"邮件发送成功","object":null}
     * 2. 邮箱未注册： MsgInfo{"status":false,"info":"用户名不存在","object":null}
     */
    @RequestMapping(value = "/findPass")
    @ResponseBody
    public MsgInfo findPass(HttpSession session, @RequestParam String id) {
        MsgInfo msgInfo = userService.findPass(id);

        if (msgInfo.getStatus()) {
            ServletContext context = session.getServletContext();
            context.setAttribute("findPass_" + msgInfo.getInfo(), msgInfo.getObject());

            msgInfo.setInfo("发送成功");
            msgInfo.setObject(null);
        }

        return msgInfo;
    }

    /**
     * 验证邮件
     *
     * @param userID userID
     * @param random 随机数，用于识别邮件
     * @return 包括：
     * 1. {"status":true,"info":"验证通过","object":null}
     * 2. {"status":false,"info":"验证失败","object":null}
     */
    @RequestMapping(value = "/verifyMail/findPass")
    @ResponseBody
    public MsgInfo verifyMail(HttpSession session, int userID, @RequestParam String random) {
        ServletContext context = session.getServletContext();

        if (random.equals(context.getAttribute("findPass_" + userID))) {
            context.removeAttribute("findPass_" + userID);
            session.setAttribute("resetPass", userID);

            return new MsgInfo(true, "验证通过");
        }

        return new MsgInfo(false, "验证失败");
    }

    /**
     * 充值密码
     *
     * @param password 新密码
     * @return 包括:
     * 1. {"status":true,"info":"重置成功","object":null}
     * 2. {"status":false,"info":"重置失败","object":null}
     * 3. {"status":false,"info":"未验证邮件","object":null}
     */
    @RequestMapping(value = "/resetPass")
    @ResponseBody
    public MsgInfo resetPass(HttpSession session, @RequestParam String password) {
        Integer userID = (Integer) session.getAttribute("resetPass");

        if (userID == null) {
            return new MsgInfo(false, "未验证邮件");
        }

        MsgInfo msgInfo = userService.resetPass(userID, password);

        if (msgInfo.getStatus()) {
            session.removeAttribute("resetPass");
        }

        return msgInfo;
    }
}
