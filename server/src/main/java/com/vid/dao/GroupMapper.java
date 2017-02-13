package com.vid.dao;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 17-2-11.
 * <p>
 * 分组相关
 */
@Service
public interface GroupMapper {

    /**
     * 获取所有分组名称
     *
     * @param username 用户名
     * @return 分组列表，无分组返回size为0的list
     */
    List<String> getAllGroup(String username);

    /**
     * 获得联系人所在分组的组名
     *
     * @param username 用户名
     * @param noteName 联系人的备注姓名
     * @return 所在分组的组名，若未分组，返回 ""
     */
    String getGroup(String username, String noteName);

    /**
     * 添加分组
     *
     * @param username  用户名
     * @param groupName 组名
     * @return 添加成功返回true，否则返回false
     */
    boolean addGroup(String username, String groupName);

    /**
     * 重命名分组
     *
     * @param username 用户名
     * @param origin   原组名
     * @param now      修改后的组名
     * @return 修改成功返回true，否则返回false
     */
    boolean renameGroup(String username, String origin, String now);

    /**
     * 删除分组
     *
     * @param username  用户名
     * @param groupName 组名
     * @return 删除成功返回true，否则返回false
     */
    boolean removeGroup(String username, String groupName);

    /**
     * 为联系人分组
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @param groupName   组名
     * @return 分组成功返回true，否则返回false
     */
    boolean groupContact(String username, String contactName, String groupName);

    /**
     * 将某个联系人从一个分组移动到另一个分组
     *
     * @param username    用户名
     * @param contactName 联系人在系统中的用户名
     * @param origin      原分组
     * @param target      目标分组
     * @return 移动成功返回true，否则返回false
     */
    boolean moveContact(String username, String contactName, String origin, String target);
}
