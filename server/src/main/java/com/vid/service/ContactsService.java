package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsDao;
import com.vid.dao.GroupDao;
import com.vid.dao.UserDao;
import com.vid.dao.VideoDao;
import com.vid.model.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by song on 17-2-7.
 * <p>
 * 联系人相关
 */
@Service
public class ContactsService {

    @Resource
    private UserDao userDao;

    @Resource
    private ContactsDao contactsDao;

    @Resource
    private GroupDao groupDao;

    @Resource
    private VideoDao videoDao;

    /**
     * 获取所有联系人
     *
     * @param userID userID，对应id字段
     */
    public MsgInfo getAllContacts(int userID) {
        User user = userDao.getUserByID(userID);

        if (user == null) {
            return new MsgInfo(false, "用户不存在");
        }

        List<Contact> contactList = contactsDao.getAllContacts(userID);
        List<Group> groupList = groupDao.getAllGroup(userID);

        AllContacts allContacts = new AllContacts(userID, user.getName(), contactList, groupList);

        return new MsgInfo(true, "", allContacts);
    }

    /**
     * 手动新建联系人，自己填写各项信息，没有视频
     *
     * @param userID，对应id字段
     * @param profile       json格式的联系人信息，可能仅包含部分信息
     */
    public MsgInfo createContact(int userID, String profile) {
        try {
            JSONObject jsonObject = new JSONObject(profile);
            String noteName = jsonObject.getString("name");

            User user = new User();
            user.setShowtelephone(jsonObject.getString("phoneNum"));
            user.setShowemail(jsonObject.getString("email"));
            user.setIndustry(jsonObject.getString("industry"));
            user.setInterest(jsonObject.getString("interest"));

            try {
                contactsDao.addContact(userID, noteName, user);
                return new MsgInfo(true, "新建成功");
            }catch (Exception e){
                e.printStackTrace();
                return new MsgInfo(false, "新建失败");
            }


        } catch (JSONException e) {
            return new MsgInfo(false, "参数错误");
        }
    }

    /**
     * 根据视频新建联系人
     *
     * @param userID  userID，对应id字段
     * @param name    联系人姓名
     * @param videoID 视频id
     */
    public MsgInfo createContactWithVideo(int userID, String name, int videoID) {
        Video video = videoDao.getVideoByID(videoID);
        if (video == null) {
            return new MsgInfo(false, "视频不存在");
        }

        int contactID = video.getOwnerid();

        if (contactID == userID) {
            return new MsgInfo(false, "无法添加自己为好友");
        }

        if (contactsDao.addContactWithVideo(userID, contactID, name, videoID)) {
            Group group = groupDao.getGroup(userID, contactID);
            User contact = userDao.getUserByID(contactID);
            List<Video> videoList = contactsDao.getAllVideos(userID, contactID);

            return new MsgInfo(true, "添加成功", new ContactProfile(name, group, contact, videoList));
        } else {
            return new MsgInfo(false, "添加失败");
        }
    }

//    /**
//     * 根据视频添加到已有联系人
//     *
//     * @param username    用户名
//     * @param contactName 联系人在系统中的用户名
//     * @param videoID     视频id
//     * @return
//     */
//    public MsgInfo addToExistingContact(String username, String contactName, String videoID) {
//        if (userMapper.getUser(contactName) == null) {
//            return new MsgInfo(false, "联系人不存在");
//        }
//
//        if (!contactsMapper.isContacts(username, contactName)) {
//            return new MsgInfo(false, "联系人不存在");
//        }
//
//        if (videoMapper.getVideoByID(videoID) == null) {
//            return new MsgInfo(false, "视频不存在");
//        }
//
//        if (contactsMapper.addToExistingContact(username, contactName, videoID)) {
//            return new MsgInfo(true, "添加成功");
//        } else {
//            return new MsgInfo(false, "添加失败");
//        }
//    }

    /**
     * 获得联系人信息
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     */
    public MsgInfo getContactsInfo(int userID, int contactID) {
        if (userDao.getUserByID(contactID) == null) {
            return new MsgInfo(false, "联系人不存在");
        }

        if (!contactsDao.isContacts(userID, contactID)) {
            return new MsgInfo(false, "非联系人");
        }

        ContactProfile profile = contactsDao.getContactInfo(userID, contactID);

        return new MsgInfo(true, "", profile);
    }

    /**
     * 编辑联系人信息
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param profile   json格式的联系人信息
     */
    public MsgInfo editContactInfo(int userID, int contactID, String profile) {
        if (userDao.getUserByID(contactID) == null) {
            return new MsgInfo(false, "联系人不存在");
        }

        if (!contactsDao.isContacts(userID, contactID)) {
            return new MsgInfo(false, "非联系人");
        }

        String noteName, phoneNum, email, industry, interest;
        try {
            JSONObject jsonObject = new JSONObject(profile);

            noteName = jsonObject.getString("noteName");
            phoneNum = jsonObject.getString("phoneNum");
            email = jsonObject.getString("email");
            industry = jsonObject.getString("industry");
            interest = jsonObject.getString("interest");

            if (contactsDao.editContactProfile(userID, contactID, noteName, phoneNum, email, industry, interest)) {
                return new MsgInfo(true, "修改成功");
            } else {
                return new MsgInfo(false, "修改失败");
            }
        } catch (JSONException e) {
            return new MsgInfo(false, "参数错误");
        }
    }

    /**
     * 删除联系人
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     */
    public MsgInfo removeContact(int userID, int contactID) {
        if (userDao.getUserByID(contactID) == null) {
            return new MsgInfo(false, "联系人不存在");
        }

        if (!contactsDao.isContacts(userID, contactID)) {
            return new MsgInfo(false, "非联系人");
        }

        if (contactsDao.removeContact(userID, contactID)) {
            return new MsgInfo(true, "删除成功");
        } else {
            return new MsgInfo(false, "删除失败");
        }
    }
}
