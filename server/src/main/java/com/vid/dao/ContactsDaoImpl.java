package com.vid.dao;

import com.vid.mapper.ContactsMapper;
import com.vid.mapper.GroupMapper;
import com.vid.mapper.UserMapper;
import com.vid.mapper.VideoMapper;
import com.vid.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
@EnableTransactionManagement
public class ContactsDaoImpl implements ContactsDao {

    @Resource
    ContactsMapper contactsMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    VideoMapper videoMapper;
    @Resource
    GroupMapper groupMapper;

    @Override
    public List<Contact> getAllContacts(int userID) {
        try {
            return contactsMapper.getAllContacts(userID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Contact>();
        }
    }

    @Override
    public boolean isContacts(int userID, int contactID) {
        try {
            if (contactsMapper.isContacts(userID, contactID) != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String getNoteName(int userID, int contactID) {
        try {
            return contactsMapper.getNoteName(userID, contactID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ContactProfile getContactInfo(int userID, int contactID) {
        try {
            List<Relationship> relationshipList = contactsMapper.getContactInfo(userID, contactID);
            // 至少存在一条记录
            Relationship relationship = relationshipList.get(0);

            Group group = groupMapper.getGroup(userID, contactID);

            // 创建临时对象，存储ContactProfile初始化时所需的数据
            User user = new User();
            user.setId(relationship.getFriendid());
            user.setShowtelephone(relationship.getNotetelephone());
            user.setShowemail(relationship.getNoteemail());
            user.setIndustry(relationship.getNoteindustry());
            user.setInterest(relationship.getNoteinterest());
            // 设置头像
            user.setImgpath(userMapper.getUserById(relationship.getFriendid()).getImgpath());

            List<Video> videoList = new ArrayList<>(relationshipList.size());
            relationshipList.forEach(temp -> videoList.add(videoMapper.getVideoByID(temp.getVideoid())));

            return new ContactProfile(relationship.getNotename(), group, user, videoList);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Video> getAllVideos(int userID, int contactID) {
        try {
            return contactsMapper.getAllVideos(userID, contactID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addContact(int userID, String noteName, User user) throws Exception {
        if (userMapper.insertUser(user) <= 0) {
            throw new Exception();
        } else if (contactsMapper.addContact(userID, noteName, user.getId(), user.getBindingtelephone(), user.getInterest(), user.getBindingemail(), user.getIndustry()) <= 0)
            throw new Exception();
        else
            return true;


    }

    @Override
    public boolean addContactWithVideo(int userID, int contactID, String noteName, int videoID) {
        try {
            User u = userMapper.getUserById(contactID);
            if (u == null)
                return false;
            if (contactsMapper.addContactWithVideo(userID, noteName, contactID, u.getBindingtelephone(), u.getInterest(), u.getBindingemail(), u.getIndustry(), videoID) > 0)
                return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editContactProfile(int userID, int contactID, String noteName, String phoneNum, String email, String industry, String interest) {
        try {
            if (contactsMapper.editContactProfile(userID, contactID, noteName, phoneNum, email, industry, interest) > 0)
                return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeContact(int userID, int contactID) {
        try {
            if (contactsMapper.removeContact(userID, contactID) > 0)
                return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
