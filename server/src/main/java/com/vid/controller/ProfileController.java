package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.model.Profile;
import com.vid.model.User;
import com.vid.service.ProfileService;
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
 * Created by song on 17-2-11.
 * <p>
 * 名片夹相关
 */
@Controller
@RequestMapping(value = "/profile", method = RequestMethod.POST)
public class ProfileController {

    @Resource
    private ProfileService profileService;

    /**
     * 获取我的名片夹信息
     *
     * @return 包括：
     * 1. MsgInfo{"status":true,"info":${username},"object":profile}
     * 2. MsgInfo{"status":false,"info":"用户不存在","object":null}
     */
    @RequestMapping
    @ResponseBody
    public MsgInfo getProfile(HttpSession session) {
        String username = (String) session.getAttribute("username");

        return profileService.getProfile(username);
    }

    /**
     * 编辑名片夹信息
     *
     * @param profile 修改后的名片夹信息
     * @return 包括
     * 1. 更新成功： MsgInfo{"status":true,"info":"更新成功","object":profile}
     * 2. 更新失败： MsgInfo{"status":false,"info":"更新失败","object":null}
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public MsgInfo editProfile(HttpSession session, String profile) {
        try {
            JSONObject jsonObject = new JSONObject(profile);
            Iterator keys = jsonObject.keys();

            while (keys.hasNext()) {
                System.out.println(keys.next().toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // TODO profile -> user
        User user = new User();
        Profile profile1 = new Profile(user);

        return profileService.updateProfile(profile1);
    }

    /**
     * 上传头像
     *
     * @param url 头像url
     * @return 上传结果，包括
     * 1. 上传成功： MsgInfo{"status":true,"info":"头像上传成功","object":null}
     * 2. 上传失败： MsgInfo{"status":false,"info":"头像上传失败","object":null}
     */
    @RequestMapping("/portrait/upload")
    @ResponseBody
    public MsgInfo uploadPortrait(HttpSession session, String url) {
        String username = (String) session.getAttribute("username");

        return profileService.uploadPortrait(username, url);
    }
}
