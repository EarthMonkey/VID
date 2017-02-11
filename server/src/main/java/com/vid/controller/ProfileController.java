package com.vid.controller;

import com.vid.config.MsgInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 获取我的名片夹信息
     *
     * @return
     */
    @RequestMapping
    @ResponseBody
    public MsgInfo getProfile(HttpSession session) {
        return new MsgInfo(true, "profile/get");
    }

    /**
     * 编辑名片夹信息
     *
     * @param profile 修改后的名片夹信息
     * @return
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
        return new MsgInfo(true, "123", profile);
    }

    /**
     * 上传头像
     *
     * @param url 头像url
     * @return
     */
    @RequestMapping("/portrait/upload")
    @ResponseBody
    public MsgInfo uploadPortrait(HttpSession session, String url) {
        return null;
    }
}
