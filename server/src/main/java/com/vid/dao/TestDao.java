package com.vid.dao;

import com.vid.mapper.ContactsMapper;
import com.vid.mapper.UserMapper;
import com.vid.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Jiayiwu on 17/2/18.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
@EnableTransactionManagement
public class TestDao {
    @Resource
    ContactsMapper contactsMapper;
    @Resource
    UserMapper userMapper;

    @Transactional(rollbackFor=Exception.class)
    public boolean addContact(int userID, String noteName, User user) {
        try {
            userMapper.insertUser(user);

            contactsMapper.addContact(userID,noteName,user.getId(),user.getBindingtelephone(),user.getInterest(),user.getBindingemail(),user.getIndustry());

            throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
