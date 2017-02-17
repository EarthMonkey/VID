package com.vid.dao;

import com.vid.model.Group;
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
    public List<Group> getAllGroup(int userID) {
        return null;
    }

    @Override
    public int getOwner(int groupID) {
        return 0;
    }

    @Override
    public Group getGroup(int userID, int contactID) {
        return null;
    }

    @Override
    public int addGroup(int userID, String groupName) {
        return 0;
    }

    @Override
    public boolean renameGroup(int groupID, String name) {
        return false;
    }

    @Override
    public boolean removeGroup(int groupID) {
        return false;
    }

    @Override
    public boolean groupContact(int userID, int contactID, int groupID) {
        return false;
    }

    @Override
    public boolean moveContact(int userID, int contactID, int origin, int target) {
        return false;
    }
}
