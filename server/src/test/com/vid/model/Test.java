package com.vid.model;

import com.vid.dao.ContactsDao;
import com.vid.dao.TestDao;
import com.vid.dao.VideoDao;
import com.vid.mapper.ContactsMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 17/2/18.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test  {

    @Resource
    VideoDao videoDao;

    @Resource
    TestDao testDao;

    @Resource
    ContactsDao contactsDao;


    @org.junit.Test
    public void test(){

//        String tem = contactsDao.getNoteName(1,3);
//        List<Video> list = contactsDao.getAllVideos(1,3);
//       boolean mc = contactsDao.addContactWithVideo(1,2,"组长",2);
        boolean mc1 = contactsDao.editContactProfile(1,2,"李胜利","13289150111","382439721@qq.com","IT业","喜欢写代码");
        boolean mc2 = contactsDao.removeContact(1,3);


    }
}
