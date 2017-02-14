package com.vid.dao;

import com.vid.model.Contact;
import com.vid.model.User;
import com.vid.model.Video;
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
     * @param userID，对应id字段
     * @return 联系人列表
     */
    List<Contact> getAllContacts(int userID);

    /**
     * 判断id为contactID的用户是否是id为userID的用户的联系人
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @return 若是，返回true，否则返回false
     */
    boolean isContacts(int userID, int contactID);

    /**
     * 获取用户userID对联系人contactID的备注姓名
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @return 若是，返回true，否则返回false
     */
    String getNoteName(int userID, int contactID);

    /**
     * 获取用户userID添加的所有属于contactID的视频
     * 不同于：
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @return 视频列表，若无视频，返回size为0的list
     * @see VideoMapper#getAllVideos(int)
     */
    List<Video> getAllVideos(int userID, int contactID);

    /**
     * 手动添加联系人，联系人不是系统用户
     * 操作步骤（事务）：
     * 1. 新建user对象
     * 2. 添加联系人
     *
     * @param userID   userID，对应id字段
     * @param noteName 联系人备注姓名
     * @param user     联系人对象，id、username、name等为null
     * @return 添加成功返回true，否则返回false
     */
    boolean addContact(int userID, String noteName, User user);

    /**
     * 通过扫描视频添加联系人，联系人已经是系统用户
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param noteName  联系人备注
     * @param videoID   视频id（对应id）
     * @return 添加成功返回true，否则返回false
     */
    boolean addContactWithVideo(int userID, int contactID, String noteName, int videoID);

    /**
     * 编辑联系人信息
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param noteName  备注
     * @param phoneNum  电话
     * @param email     邮箱
     * @param industry  行业
     * @param interest  兴趣
     * @return
     */
    boolean editContactProfile(int userID, int contactID, String noteName, String phoneNum,
                               String email, String industry, String interest);

//    /**
//     * 扫描视频添加到已有联系人
//     *
//     * @param username    用户名
//     * @param contactName 联系人在系统中的用户名
//     * @param videoID     视频id（对应id）
//     * @return 添加成功返回true，否则返回false
//     */
//    boolean addToExistingContact(String username, String contactName, String videoID);

    /**
     * 删除联系人
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @return 删除成功返回true，否则返回true
     */
    boolean removeContact(int userID, int contactID);
}
