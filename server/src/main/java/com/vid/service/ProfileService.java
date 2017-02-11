package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.UserMapper;
import com.vid.model.Profile;
import com.vid.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by song on 17-2-11.
 * <p>
 * 用户个人信息相关
 */
@Service
public class ProfileService {

    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户信息
     *
     * @param username 用户名
     */
    public MsgInfo getProfile(String username) {
        User user = userMapper.getUser(username);

        if (user != null) {
            return new MsgInfo(true, username, new Profile(user));
        } else {
            return new MsgInfo(false, "用户不存在");
        }
    }

    /**
     * 更新用户信息
     *
     * @param profile 用户信息
     */
    public MsgInfo updateProfile(Profile profile) {
        User user = userMapper.getUser(profile.getUserName());

        user.setName(profile.getName());
        user.setShowemail(profile.getEmail());
        user.setShowtelephone(profile.getPhoneNum());

        if (userMapper.updateUser(user)) {
            return new MsgInfo(true, "更新成功", profile);
        } else {
            return new MsgInfo(false, "更新失败");
        }
    }

    /**
     * 上传头像
     *
     * @param username    用户名
     * @param portraitURL 头像url
     */
    public MsgInfo uploadPortrait(String username, String portraitURL) {
        if (userMapper.setPortraitURL(username, portraitURL)) {
            return new MsgInfo(true, "头像上传成功");
        } else {
            return new MsgInfo(false, "头像上传失败");
        }
    }
}
