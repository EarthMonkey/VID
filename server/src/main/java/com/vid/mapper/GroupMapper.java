package com.vid.mapper;

import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

public interface GroupMapper {

    public List<String> getAllGroup(int userID);

    String getGroup(int userID, int contactID);

    int addGroup(int userID, String groupName);

    int renameGroup(int userID, String origin, String now);

    int removeGroup(int userID, String groupName);



}
