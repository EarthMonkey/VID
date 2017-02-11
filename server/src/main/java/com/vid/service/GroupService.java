package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.ContactsMapper;
import com.vid.dao.GroupMapper;
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
    private GroupMapper groupMapper;

    @Resource
    private ContactsMapper contactsMapper;

    /**
     * 添加分组
     *
     * @param username  用户名
     * @param groupName 组名
     * @return 添加成功返回true，否则返回false
     */
    public MsgInfo addGroup(String username, String groupName) {
        List<String> groupList = groupMapper.getAllGroup(username);

        if (groupList.contains(groupName)) {
            return new MsgInfo(false, "分组已存在");
        }

        if (groupMapper.addGroup(username, groupName)) {
            return new MsgInfo(true, "分组添加成功");
        }

        return new MsgInfo(false, "未知错误");
    }

    /**
     * 重命名分组
     *
     * @param username 用户名
     * @param origin   原组名
     * @param now      修改后的组名
     * @return 修改成功返回true，否则返回false
     */
    public MsgInfo renameGroup(String username, String origin, String now) {
        List<String> groupList = groupMapper.getAllGroup(username);

        if (groupList.contains(origin)) {
            if (groupList.contains(now)) {
                return new MsgInfo(false, "组名已存在");
            }

            if (groupMapper.renameGroup(username, origin, now)) {
                return new MsgInfo(true, "组名修改成功");
            }
        }

        return new MsgInfo(false, "分组不存在");
    }

    /**
     * 删除分组
     *
     * @param username  用户名
     * @param groupName 组名
     * @return 删除成功返回true，否则返回false
     */
    public MsgInfo removeGroup(String username, String groupName) {
        List<String> groupList = groupMapper.getAllGroup(username);

        if (groupList.contains(groupName)) {
            if (groupMapper.removeGroup(username, groupName)) {
                return new MsgInfo(true, "分组删除成功");
            }
        }

        return new MsgInfo(false, "分组不存在");
    }

    /**
     * 为联系人分组
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @param groupName   组名
     * @return 分组成功返回true，否则返回false
     */
    public MsgInfo groupContact(String username, String contactName, String groupName) {
        // 判断是否为联系人
        if (!contactsMapper.isContacts(username, contactName)) {
            return new MsgInfo(false, contactName + "不是" + username + "的联系人");
        }

        // 判断分组是否存在
        List<String> groupList = groupMapper.getAllGroup(username);
        if (!groupList.contains(groupName)) {
            return new MsgInfo(false, "分组不存在");
        }

        if (groupMapper.groupContact(username, contactName, groupName)) {
            return new MsgInfo(true, "分组成功");
        }

        return new MsgInfo(false, "未知原因");
    }

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @param origin      原分组
     * @param target      目标分组
     * @return 移动成功返回true，否则返回false
     */
    public MsgInfo moveContact(String username, String contactName, String origin, String target) {
        // 判断是否为联系人
        if (!contactsMapper.isContacts(username, contactName)) {
            return new MsgInfo(false, contactName + "不是" + username + "的联系人");
        }

        // 判断分组是否存在
        List<String> groupList = groupMapper.getAllGroup(username);
        if (!groupList.contains(origin)) {
            return new MsgInfo(false, "分组不存在");
        }
        if (!groupList.contains(target)) {
            return new MsgInfo(false, "目标分组不存在");
        }

        if (groupMapper.moveContact(username, contactName, origin, target)) {
            return new MsgInfo(true, "移动分组成功");
        }

        return new MsgInfo(false, "未知原因");
    }
}
