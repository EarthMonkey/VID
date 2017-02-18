package com.vid.mapper;

import com.vid.model.Group;

import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

public interface GroupMapper {

    public List<Group> getAllGroup(int userID);

    public int getOwner(int groupID);

    public Group getGroup(int userID, int contactID);

    public int addGroup(Group group);

    public int renameGroup(int groupID, String name);

    public int removeGroup(int groupID);

    public int groupContact(int userID, int contactID, int groupID);

    public int moveContact(int userID, int contactID, int origin, int target);


}
