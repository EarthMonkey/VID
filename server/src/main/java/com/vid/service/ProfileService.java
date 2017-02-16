package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.UserDao;
import com.vid.model.Profile;
import com.vid.model.User;
import org.json.JSONException;
import org.json.JSONObject;
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
    private UserDao userDao;

    /**
     * 获取用户信息
     *
     * @param userID 用户的userID
     */
    public MsgInfo getProfile(int userID) {
        User user = userDao.getUserByID(userID);

        if (user != null) {
            return new MsgInfo(true, "", new Profile(user));
        } else {
            return new MsgInfo(false, "用户不存在");
        }
    }

    /**
     * 更新用户信息
     *
     * @param userID  用户的ID
     * @param profile json格式用户信息
     */
    public MsgInfo updateProfile(int userID, String profile) {
        User user = userDao.getUserByID(userID);

        try {
            JSONObject jsonObject = new JSONObject(profile);

            user.setName(jsonObject.getString("name"));
            user.setShowemail(jsonObject.getString("email"));
            user.setShowtelephone(jsonObject.getString("phoneNum"));
            user.setIndustry(jsonObject.getString("industry"));
            user.setInterest(jsonObject.getString("interest"));

            if (userDao.updateUser(user)) {
                return new MsgInfo(true, "更新成功");
            } else {
                return new MsgInfo(false, "更新失败");
            }
        } catch (JSONException e) {
            return new MsgInfo(false, "参数错误");
        }
    }

    /**
     * 上传头像
     *
     * @param userID      用户的userID
     * @param portraitURL 头像url
     */
    public MsgInfo uploadPortrait(int userID, String portraitURL) {
        if (userDao.setPortraitURL(userID, portraitURL)) {
            return new MsgInfo(true, "上传成功");
        } else {
            return new MsgInfo(false, "上传失败");
        }
    }
}
