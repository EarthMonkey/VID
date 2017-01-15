package com.vid.service;

import com.vid.dao.UserMapper;
import com.vid.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public List<User> getUsers(){

        return  userMapper.getUsers();


    }
}
