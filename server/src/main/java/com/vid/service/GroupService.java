package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsDao;
import com.vid.dao.GroupDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by song on 17-2-11.
 * <p>
 * 分组相关
 */
@Service
public class GroupService {

    @Resource
    private GroupDao groupDao;

    @Resource
    private ContactsDao contactsDao;

    /**
     * 添加分组
     *
     * @param userID    userID, 对应id字段
     * @param groupName 组名
     */
    public MsgInfo addGroup(int userID, String groupName) {
        List<String> groupList = groupDao.getAllGroup(userID);

        if (groupList.contains(groupName)) {
            return new MsgInfo(false, "分组已存在");
        }

        if (groupDao.addGroup(userID, groupName)) {
            return new MsgInfo(true, "添加成功");
        }

        return new MsgInfo(false, "未知错误");
    }

    /**
     * 重命名分组
     *
     * @param userID userID，对应id字段
     * @param origin 原组名
     * @param now    修改后的组名
     */
    public MsgInfo renameGroup(int userID, String origin, String now) {
        List<String> groupList = groupDao.getAllGroup(userID);

        if (groupList.contains(origin)) {
            if (groupList.contains(now)) {
                return new MsgInfo(false, "组名已存在");
            }

            if (groupDao.renameGroup(userID, origin, now)) {
                return new MsgInfo(true, "修改成功");
            }
        }

        return new MsgInfo(false, "原组不存在");
    }

    /**
     * 删除分组
     *
     * @param userID    userID，对应id字段
     * @param groupName 组名
     */
    public MsgInfo removeGroup(int userID, String groupName) {
        List<String> groupList = groupDao.getAllGroup(userID);

        if (groupList.contains(groupName)) {
            if (groupDao.removeGroup(userID, groupName)) {
                return new MsgInfo(true, "删除成功");
            }
        }

        return new MsgInfo(false, "分组不存在");
    }

    /**
     * 为联系人分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param groupName 组名
     * @return 分组成功返回true，否则返回false
     */
    public MsgInfo groupContact(int userID, int contactID, String groupName) {
        // 判断是否为联系人
        if (!contactsDao.isContacts(userID, contactID)) {
            return new MsgInfo(false, "非联系人");
        }

        // 判断分组是否存在
        List<String> groupList = groupDao.getAllGroup(userID);
        if (!groupList.contains(groupName)) {
            return new MsgInfo(false, "分组不存在");
        }

        if (groupDao.groupContact(userID, contactID, groupName)) {
            return new MsgInfo(true, "分组成功");
        }

        return new MsgInfo(false, "分组失败");
    }

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param origin    原分组
     * @param target    目标分组
     * @return 移动成功返回true，否则返回false
     */
    public MsgInfo moveContact(int userID, int contactID, String origin, String target) {
        // 判断是否为联系人
        if (!contactsDao.isContacts(userID, contactID)) {
            return new MsgInfo(false, "非联系人");
        }

        // 判断分组是否存在
        List<String> groupList = groupDao.getAllGroup(userID);
        if (!groupList.contains(origin)) {
            return new MsgInfo(false, "原组不存在");
        }
        if (!groupList.contains(target)) {
            return new MsgInfo(false, "目标分组不存在");
        }

        if (groupDao.moveContact(userID, contactID, origin, target)) {
            return new MsgInfo(true, "移动成功");
        }

        return new MsgInfo(false, "移动失败");
    }
}
