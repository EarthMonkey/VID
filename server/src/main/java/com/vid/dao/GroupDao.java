package com.vid.dao;

import com.vid.model.Group;

import java.util.List;

/**
 * Created by song on 17-2-11.
 * <p>
 * 分组相关
 */
public interface GroupDao {

    /**
     * 获取所有分组名称
     *
     * @param userID userID，对应id字段
     * @return 分组列表，无分组返回size为0的list
     */
    List<Group> getAllGroup(int userID);

    /**
     * 获得分组的拥有者
     *
     * @param groupID groupID，对应id字段
     * @return 拥有者的userID，若分组不存在，返回-1
     */
    int getOwner(int groupID);

    /**
     * 获得联系人所在分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @return 所在分组，若未分组，返回null
     */
    Group getGroup(int userID, int contactID);

    /**
     * 添加分组
     *
     * @param userID    userID，对应id字段
     * @param groupName 组名
     * @return groupID
     */
    int addGroup(int userID, String groupName);

    /**
     * 重命名分组
     *
     * @param groupID groupID，对应id字段
     * @param name    修改后的组名
     * @return 修改成功返回true，否则返回false
     */
    boolean renameGroup(int groupID, String name);

    /**
     * 删除分组
     *
     * @param groupID groupID，对应id字段
     * @return 删除成功返回true，否则返回false
     */
    boolean removeGroup(int groupID);

    /**
     * 为联系人分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param groupID   groupID，对应ID字段
     * @return 分组成功返回true，否则返回false
     */
    boolean groupContact(int userID, int contactID, int groupID);

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param origin    原分组groupID
     * @param target    目标分组groupID
     * @return 移动成功返回true，否则返回false
     */
    boolean moveContact(int userID, int contactID, int origin, int target);
}
