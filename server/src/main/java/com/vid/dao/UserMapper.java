package com.vid.dao;

import com.vid.model.User;

import java.util.List;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface UserMapper {
    public void insertUser(User user);

    public User getUser(String name);

    public List<User> getUsers();
}
