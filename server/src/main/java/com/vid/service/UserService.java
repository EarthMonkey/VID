package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsDao;
import com.vid.dao.GroupDao;
import com.vid.dao.UserDao;
import com.vid.model.AllContacts;
import com.vid.model.Contact;
import com.vid.model.Group;
import com.vid.model.User;
import com.vid.utils.SHA256;
import com.vid.utils.mail.MailFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
@EnableTransactionManagement
@Transactional(rollbackFor = Exception.class)
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private ContactsDao contactsDao;

    @Resource
    private GroupDao groupDao;

    /**
     * 用户注册
     *
     * @param email    邮箱
     * @param phoneNum 电话，可能为null
     * @param password 密码
     * @param name     用户名
     * @return 注册结果
     */
    public MsgInfo register(String email, String phoneNum, String password, String name) {
        User temp = userDao.getUser(email);

        if (temp != null) {
            return new MsgInfo(false, "邮箱已被注册");
        }

        int userID = userDao.insertUser(new User(email, phoneNum, SHA256.encrypt(password), name));

        // 插入成功，发送邮件
        if (userID != -1) {
            String random = SHA256.encrypt(new Random().nextLong() + "");

            if (MailFactory.activateAccount(email, userID, name, random)) {
                return new MsgInfo(true, random, userID);
            }
        }

        return new MsgInfo(false, "无效的邮箱地址");
    }

    /**
     * 激活账户
     *
     * @param userID userID
     */
    public MsgInfo activateAccount(int userID) {
        if (userDao.activateAccount(userID)) {
            return new MsgInfo(true, "激活成功");
        } else {
            return new MsgInfo(false, "激活失败");
        }
    }

    /**
     * 用户登录
     *
     * @param id       用户手机号或邮箱或用户名
     * @param password 密码
     * @return 登录结果
     */
    public MsgInfo login(String id, String password) {
        User user = userDao.getUser(id);

        if (user == null) {
            return new MsgInfo(false, "用户名不存在");
        }

        if (SHA256.encrypt(password).equals(user.getPassword())) {
            if (!userDao.isActive(user.getId())) {
                return new MsgInfo(false, "账户未激活", user.getBindingemail());
            }

            List<Contact> contactList = contactsDao.getAllContacts(user.getId());
            List<Group> groupList = groupDao.getAllGroup(user.getId());

            AllContacts allContacts = new AllContacts(user.getId(), user.getName(), contactList, groupList);

            return new MsgInfo(true, "登录成功", allContacts);
        } else {
            return new MsgInfo(false, "密码错误");
        }
    }

    /**
     * 找回密码
     *
     * @param id 用户名/邮箱/密码
     */
    public MsgInfo findPass(String id) {
        User user = userDao.getUser(id);

        if (user == null) {
            return new MsgInfo(false, "用户不存在");
        }

        // 随机数，用于识别邮件的真实性
        String random = SHA256.encrypt(new Random().nextLong() + "");

        // 由于需要传userID和random，所以临时使用MsgInfo.info进行传值
        if (MailFactory.findPass(user.getBindingemail(), user.getId(), user.getName(), random)) {
            return new MsgInfo(true, "" + user.getId(), random);
        } else {
            return new MsgInfo(false, "邮件发送失败");
        }
    }

    /**
     * 重置密码
     *
     * @param userID   userID
     * @param password 密码
     */
    public MsgInfo resetPass(int userID, String password) {
        if (userDao.resetPass(userID, password)) {
            return new MsgInfo(true, "重置成功");
        } else {
            return new MsgInfo(false, "重置失败");
        }
    }
}
