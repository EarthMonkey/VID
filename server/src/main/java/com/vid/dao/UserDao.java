package com.vid.dao;

import com.vid.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface UserDao {

    /**
     * 新建用户
     *
     * @param user 用户实体类，初始值仅包含
     *             手机号/邮箱 + 密码
     * @return 返回用户的id，插入失败返回 -1
     */
    int insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 更新后的user对象
     * @return 更新成功返回true，否则返回false
     */
    boolean updateUser(User user);

    /**
     * 重置密码
     *
     * @param userID   userID
     * @param password 密码
     * @return 成功返回true，否则返回false
     */
    boolean resetPass(int userID, String password);

    /**
     * 根据手机号/邮箱/用户名查询用户信息
     *
     * @param id 手机号或邮箱或用户名，不同于userID
     * @return 无查询结果返回null
     */
    User getUser(String id);

    /**
     * 根据userID获取user对象
     *
     * @param userID userID，对应id字段
     * @return user对象，无查询结果返回null
     */
    User getUserByID(int userID);

    /**
     * 设置头像url
     *
     * @param userID      userID，对应id字段
     * @param portraitURL 头像url
     * @return 设置成功返回true，否则返回false
     */
    boolean setPortraitURL(int userID, String portraitURL);

    /**
     * 判断用户账号是否激活
     *
     * @param userID userID，对应id字段
     * @return 激活返回true， 否则返回false
     */
    boolean isActive(int userID);

    /**
     * 激活账户
     *
     * @param userID userID，对应id字段
     * @return 激活返回true， 否则返回false
     */
    boolean activateAccount(int userID);
}
