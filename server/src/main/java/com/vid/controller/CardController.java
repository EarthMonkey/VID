package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.model.User;
import com.vid.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by song on 17-2-7.
 * <p>
 * 名片夹相关
 */
@Controller
public class CardController {

    @Resource
    CardService cardService;

    /**
     * 获取个人简介
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping("/profile")
    @ResponseBody
    public MsgInfo getProfile(String id) {
        return new MsgInfo(true, "profile");
    }

    /**
     * 获取视频二维码
     *
     * @param videoID 视频id
     * @return
     */
    @RequestMapping("/video/code")
    @ResponseBody
    public MsgInfo getCode(String videoID) {
        return null;
    }

    /**
     * 编辑个人信息
     *
     * @param id   用户id
     * @param user 用户信息
     * @return
     */
    @RequestMapping("/profile/edit")
    @ResponseBody
    public MsgInfo editProfile(String id, User user) {
        return new MsgInfo(true, "profile/edit");
    }
}
