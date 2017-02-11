package com.vid.dao;

import com.vid.model.User;

import java.util.List;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface UserMapper {

    /**
     * 新建用户
     *
     * @param user 用户实体类，初始值仅包含三项
     *             手机号/邮箱 + 用户名 + 密码
     */
    void insertUser(User user);

    /**
     * 根据手机号/邮箱/用户名查询用户信息
     *
     * @param id 手机号或邮箱或用户名
     * @return 无查询结果返回null
     */
    User getUser(String id);

    List<User> getUsers();
}
