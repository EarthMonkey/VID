package com.vid.mapper;

import com.vid.model.User;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface UserMapper {

    public int insertUser(User user);

    public int updateUser(User user);

    public int resetPass(int userID,String password);


    public User getUserByEmail(String email);

    public User getUserByTelephone(String telephone);

    public User getUserById(int userID);

    public int setPortraitURL(int userID,String portraitURL);

    public int isActive(int userID);

    public int activateAccount(int userID);
}
