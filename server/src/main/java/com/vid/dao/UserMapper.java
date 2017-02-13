package com.vid.dao;

import com.vid.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public interface UserMapper {

    /**
     * 新建用户
     *
     * @param user 用户实体类，初始值仅包含三项
     *             手机号/邮箱 + 用户名 + 密码
     * @return 返回用户的id，插入失败返回 ""
     */
    String insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 更新后的user对象
     * @return 更新成功返回true，否则返回false
     */
    boolean updateUser(User user);

    /**
     * 根据手机号/邮箱/用户名查询用户信息
     *
     * @param id 手机号或邮箱或用户名
     * @return 无查询结果返回null
     */
    User getUser(String id);

    /**
     * 设置头像url
     *
     * @param username    用户名
     * @param portraitURL 头像url
     * @return 设置成功返回true，否则返回false
     */
    boolean setPortraitURL(String username, String portraitURL);
}
