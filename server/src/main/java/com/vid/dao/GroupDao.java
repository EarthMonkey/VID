package com.vid.dao;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 17-2-11.
 * <p>
 * 分组相关
 */
@Service
public interface GroupDao {

    /**
     * 获取所有分组名称
     *
     * @param userID，对应id字段
     * @return 分组列表，无分组返回size为0的list
     */
    List<String> getAllGroup(int userID);

    /**
     * 获得联系人所在分组的组名
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @return 所在分组的组名，若未分组，返回size为0的list
     */
    String getGroup(int userID, int contactID);

    /**
     * 添加分组
     *
     * @param userID    userID，对应id字段
     * @param groupName 组名
     * @return 添加成功返回true，否则返回false
     */
    boolean addGroup(int userID, String groupName);

    /**
     * 重命名分组
     *
     * @param userID userID，对应id字段
     * @param origin 原组名
     * @param now    修改后的组名
     * @return 修改成功返回true，否则返回false
     */
    boolean renameGroup(int userID, String origin, String now);

    /**
     * 删除分组
     *
     * @param userID    userID，对应id字段
     * @param groupName 组名
     * @return 删除成功返回true，否则返回false
     */
    boolean removeGroup(int userID, String groupName);

    /**
     * 为联系人分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param groupName 组名
     * @return 分组成功返回true，否则返回false
     */
    boolean groupContact(int userID, int contactID, String groupName);

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param userID    userID，对应id字段
     * @param contactID 联系人的userID
     * @param origin    原分组
     * @param target    目标分组
     * @return 移动成功返回true，否则返回false
     */
    boolean moveContact(int userID, int contactID, String origin, String target);
}
