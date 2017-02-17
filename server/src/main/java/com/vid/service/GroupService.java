package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsDao;
import com.vid.dao.GroupDao;
import com.vid.model.Group;
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
        List<Group> groupList = groupDao.getAllGroup(userID);

        if (hasGroupName(groupList, groupName)) {
            return new MsgInfo(false, "已存在同名分组");
        }

        int groupID = groupDao.addGroup(userID, groupName);

        return new MsgInfo(true, "添加成功", groupID);
    }

    /**
     * 重命名分组
     *
     * @param userID  userID，对应id字段
     * @param groupID groupID
     * @param name    修改后的组名
     */
    public MsgInfo renameGroup(int userID, int groupID, String name) {
        if (!isOwner(userID, groupID)) {
            return new MsgInfo(false, "分组不存在");
        }

        List<Group> groupList = groupDao.getAllGroup(userID);

        if (hasGroupName(groupList, name)) {
            return new MsgInfo(false, "已存在同名分组");
        } else {
            if (groupDao.renameGroup(groupID, name)) {
                return new MsgInfo(true, "修改成功");
            } else {
                return new MsgInfo(false, "修改失败");
            }
        }
    }

    /**
     * 删除分组
     *
     * @param userID  userID，对应id字段
     * @param groupID groupID
     */
    public MsgInfo removeGroup(int userID, int groupID) {
        if (!isOwner(userID, groupID)) {
            return new MsgInfo(false, "分组不存在");
        }

        if (groupDao.removeGroup(groupID)) {
            return new MsgInfo(true, "删除成功");
        } else {
            return new MsgInfo(false, "删除失败");
        }
    }

    /**
     * 为联系人分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param groupID   groupID
     * @return 分组成功返回true，否则返回false
     */
    public MsgInfo groupContact(int userID, int contactID, int groupID) {
        // 判断是否为联系人
        if (!contactsDao.isContacts(userID, contactID)) {
            return new MsgInfo(false, "非联系人");
        }

        if (!isOwner(userID, groupID)) {
            return new MsgInfo(false, "分组不存在");
        }

        if (groupDao.groupContact(userID, contactID, groupID)) {
            return new MsgInfo(true, "分组成功");
        } else {
            return new MsgInfo(false, "分组失败");
        }
    }

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param origin    原分组groupID
     * @param target    目标分组groupID
     * @return 移动成功返回true，否则返回false
     */
    @Deprecated
    public MsgInfo moveContact(int userID, int contactID, int origin, int target) {
        // 判断是否为联系人
        if (!contactsDao.isContacts(userID, contactID)) {
            return new MsgInfo(false, "非联系人");
        }

        if (!isOwner(userID, origin)) {
            return new MsgInfo(false, "原分组不存在");
        }

        if (!isOwner(userID, target)) {
            return new MsgInfo(false, "目标分组不存在");
        }

        if (groupDao.moveContact(userID, contactID, origin, target)) {
            return new MsgInfo(true, "移动成功");
        }

        return new MsgInfo(false, "移动失败");
    }

    /**
     * 判断用户是否有和groupName相同的分组
     *
     * @param groupList 分组列表
     * @param groupName 待检验的组名
     * @return 若存在同名，返回true，否则返回false
     */
    private boolean hasGroupName(List<Group> groupList, String groupName) {
        for (Group group : groupList) {
            if (groupName.equals(group.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断用户是否拥有groupID的分组
     *
     * @param userID  用户id
     * @param groupID groupID
     * @return 若是，返回true，否则返回false
     */
    private boolean isOwner(int userID, int groupID) {
        int temp = groupDao.getOwner(groupID);

        return temp == userID;
    }
}
