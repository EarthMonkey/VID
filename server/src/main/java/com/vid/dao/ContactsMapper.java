package com.vid.dao;

import com.vid.model.Contact;
import com.vid.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 17-2-10.
 * <p>
 * 联系人相关
 */
@Service
public interface ContactsMapper {

    /**
     * 获取所有联系人
     *
     * @param username 用户名
     * @return 联系人列表
     */
    List<Contact> getAllContacts(String username);

    /**
     * 判断contactName是否是username的联系人
     *
     * @param username    用户名
     * @param contactName 联系人的用户名
     * @return 若是，返回true，否则返回false
     */
    boolean isContacts(String username, String contactName);

    /**
     * 判断用户是否有备注名为noteName的联系人
     *
     * @param username 用户名
     * @param noteName 联系人的备注姓名
     * @return 若有，返回true，否则返回false
     */
    boolean hasContact(String username, String noteName);

    /**
     * 手动添加联系人，联系人不是系统用户
     * 操作步骤（事务）：
     * 1. 新建user对象
     * 2. 添加联系人
     *
     * @param username 用户名
     * @param noteName 联系人备注姓名
     * @param user     联系人对象，id、username、name等为null
     * @return 添加成功返回true，否则返回false
     */
    boolean addContact(String username, String noteName, User user);

    /**
     * 通过扫描视频添加联系人，联系人已经是系统用户
     *
     * @param username 用户名
     * @param noteName 联系人备注
     * @param videoID  视频id（对应id）
     * @return 添加成功返回true，否则返回false
     */
    boolean addContactWithVideo(String username, String noteName, String videoID);

    /**
     * 扫描视频添加到已有联系人
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @param videoID     视频id（对应id）
     * @return 添加成功返回true，否则返回false
     */
    boolean addToExistingContact(String username, String contactName, String videoID);

    /**
     * 根据备注姓名获得该联系人的User对象
     *
     * @param username 用户名
     * @param noteName 联系人的备注姓名
     * @return 联系人的User对象
     */
    User getContact(String username, String noteName);
}
