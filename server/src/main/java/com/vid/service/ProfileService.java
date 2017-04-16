package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsDao;
import com.vid.dao.UserDao;
import com.vid.dao.VideoDao;
import com.vid.model.Profile;
import com.vid.model.User;
import com.vid.model.Video;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 17-2-11.
 * <p>
 * 用户个人信息相关
 */
@Service
public class ProfileService {

    @Resource
    private UserDao userDao;

    @Resource
    private VideoDao videoDao;

    @Resource
    private ContactsDao contactsDao;

    /**
     * 获取用户信息
     *
     * @param userID 用户的userID
     */
    public MsgInfo getProfile(int userID) {
        User user = userDao.getUserByID(userID);

        if (user != null) {
            List<Video> videoList = videoDao.getAllVideos(userID);

            return new MsgInfo(true, "", new Profile(user, videoList));
        } else {
            return new MsgInfo(false, "用户不存在");
        }
    }

    /**
     * 获取其他用户的信息，包括：
     * 姓名，电话，邮箱
     */
    public MsgInfo getContactProfile(int contactID) {
        User contact = userDao.getUserByID(contactID);

        if (contact != null) {
            Map<String, String> profile = new HashMap<>();

            profile.put("name", contact.getName());
            profile.put("phoneNum", contact.getBindingtelephone());
            profile.put("mail", contact.getBindingemail());
            profile.put("portrait", contact.getImgpath());
            // 备注， interest字段存储
            profile.put("note", contact.getInterest());

            return new MsgInfo(true, "", profile);
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

            if ("".equals(jsonObject.getString("name"))) {
                return new MsgInfo(false, "姓名不能为空");
            }

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
