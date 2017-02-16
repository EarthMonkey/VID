package com.vid.dao;

import com.vid.mapper.UserMapper;
import com.vid.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
public class UserDaoImpl implements UserDao {

    static String EMAIL_RE = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @Resource
    UserMapper userMapper;

    @Override
    public String insertUser(User user) {
        try {
            if (userMapper.insertUser(user) > 0)
                return user.getId() + "";
            else
                return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    @Override
    public boolean updateUser(User user) {

        try {

            if (userMapper.updateUser(user) > 0)
                return true;
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean resetPass(int userID, String password) {
        try {
            if (userMapper.resetPass(userID, password) > 0)
                return true;
            else
                return false;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUser(String id) {
        try {
            if (id.matches(EMAIL_RE))
                return userMapper.getUserByEmail(id);
            else
                return userMapper.getUserByTelephone(id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User getUserByID(int userID) {
        try {

            return getUserByID(userID);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean setPortraitURL(int userID, String portraitURL) {
        try {

            if (userMapper.setPortraitURL(userID, portraitURL) > 0)
                return true;
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
