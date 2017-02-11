package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsMapper;
import com.vid.model.Contact;
import com.vid.model.ContactProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 17-2-7.
 * <p>
 * 联系人相关
 */
@Service
@EnableTransactionManagement
@Transactional(rollbackFor = Exception.class)
public class ContactsService {

    @Resource
    ContactsMapper contactsMapper;

    /**
     * 获取所有联系人
     *
     * @param username 用户名
     * @return
     */
    public MsgInfo getAllContacts(String username) {
//        List<Contact> contacts = contactsMapper.getAllContacts(username);

//        contacts.sort(Comparator.comparing(Contact::getName));

        List<Contact> contactList = new ArrayList<>(10);

        contactList.add(new Contact("张三", "1", "1"));
        contactList.add(new Contact("李四", "1", "1"));
        contactList.add(new Contact("王五", "1", "1"));

        return new MsgInfo(true, "123", contactList);
    }

    /**
     * 手动新建联系人，自己填写各项信息，没有视频
     *
     * @param username 用户名
     * @param profile  联系人信息
     * @return
     */
    public MsgInfo createContact(String username, ContactProfile profile) {
        return null;
    }

    /**
     * 根据视频新建联系人
     *
     * @param username 用户名
     * @param name     联系人姓名（备注）
     * @param videoID  视频id
     * @return
     */
    public MsgInfo createContact(String username, String name, String videoID) {
        return null;
    }

    /**
     * 根据视频添加到已有联系人
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @param videoID     视频id
     * @return
     */
    public MsgInfo addToExistingContact(String username, String contactName, String videoID) {
        return null;
    }

    /**
     * 获得联系人信息
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @return
     */
    public MsgInfo getContactsInfo(String username, String contactName) {
        return null;
    }

    /**
     * 编辑联系人信息
     *
     * @param username 用户名
     * @param profile  联系人信息
     * @return
     */
    public MsgInfo editContactInfo(String username, ContactProfile profile) {
        return null;
    }

    /**
     * 删除联系人
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @return
     */
    public MsgInfo removeContact(String username, String contactName) {
        return null;
    }

    /**
     * 搜索联系人
     *
     * @param username      用户名
     * @param searchContent 搜索内容
     * @return
     */
    public MsgInfo searchContact(String username, String searchContent) {
        return null;
    }
}
