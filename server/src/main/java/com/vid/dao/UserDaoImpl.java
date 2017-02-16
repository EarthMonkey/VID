package com.vid.dao;

import com.vid.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public String insertUser(User user) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean resetPass(int userID, String password) {
        return false;
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public User getUserByID(int userID) {
        return null;
    }

    @Override
    public boolean setPortraitURL(int userID, String portraitURL) {
        return false;
    }
}
