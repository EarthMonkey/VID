package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsMapper;
import com.vid.dao.GroupMapper;
import com.vid.dao.UserMapper;
import com.vid.model.AllContacts;
import com.vid.model.Contact;
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
    private UserMapper userMapper;

    @Resource
    private ContactsMapper contactsMapper;

    @Resource
    private GroupMapper groupMapper;

    /**
     * 用户注册
     *
     * @param id       用户注册时使用的手机号或邮箱
     * @param password 密码
     * @param name     用户名
     * @return 注册结果
     */
    public MsgInfo register(String id, String password, String name) {
        User user = userMapper.getUser(id);

        if (user != null) {
            return new MsgInfo(false, "用户名已存在");
        }

        if (!"".equals(userMapper.insertUser(new User(id, SHA256.encrypt(password), name)))) {
            return new MsgInfo(true, "注册成功");
        } else {
            return new MsgInfo(false, "注册失败");
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
        User user = userMapper.getUser(id);

        if (user == null) {
            return new MsgInfo(false, "用户名不存在");
        }

        if (SHA256.encrypt(password).equals(user.getPassword())) {
            List<Contact> contactList = contactsMapper.getAllContacts(user.getId());
            List<String> groupList = groupMapper.getAllGroup(user.getId());

            AllContacts allContacts = new AllContacts(user.getId(), user.getUsername(), contactList, groupList);

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
        User user = userMapper.getUser(id);

        if (user == null) {
            return new MsgInfo(false, "用户不存在");
        }

        // 随机数，用于识别邮件的真实性
        String random = SHA256.encrypt(new Random().nextLong() + "");

        // 由于需要传userID和random，所以临时使用MsgInfo.info进行传值
        if (MailFactory.findPass(user.getBindingemail(), user.getId(), user.getUsername(), random)) {
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
        if (userMapper.resetPass(userID, password)) {
            return new MsgInfo(true, "重置成功");
        } else {
            return new MsgInfo(false, "重置失败");
        }
    }
}
