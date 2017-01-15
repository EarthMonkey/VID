package com.vid.service;

import com.vid.dao.UserMapper;
import com.vid.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Service
@EnableTransactionManagement
@Transactional(rollbackFor=Exception.class)
public class UserService {

    @Resource
    UserMapper userMapper;


    public List<User> getUsers(){

        return  userMapper.getUsers();


    }


    public void insertUser(User user) throws Exception{
        userMapper.insertUser(user);

            throw  new Exception();




    }
}
