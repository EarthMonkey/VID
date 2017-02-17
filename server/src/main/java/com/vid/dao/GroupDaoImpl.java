package com.vid.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
public class GroupDaoImpl implements GroupDao {
    @Override
    public List<String> getAllGroup(int userID) {
        return null;
    }

    @Override
    public String getGroup(int userID, int contactID) {
        return null;
    }

    @Override
    public boolean addGroup(int userID, String groupName) {
        return false;
    }

    @Override
    public boolean renameGroup(int userID, String origin, String now) {
        return false;
    }

    @Override
    public boolean removeGroup(int userID, String groupName) {
        return false;
    }

    @Override
    public boolean groupContact(int userID, int contactID, String groupName) {
        return false;
    }

    @Override
    public boolean moveContact(int userID, int contactID, String origin, String target) {
        return false;
    }
}
