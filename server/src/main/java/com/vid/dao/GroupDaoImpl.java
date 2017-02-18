package com.vid.dao;

import com.vid.mapper.GroupMapper;
import com.vid.model.Group;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
public class GroupDaoImpl implements GroupDao {

    @Resource
    GroupMapper groupMapper;

    @Override
    public List<Group> getAllGroup(int userID) {

        try {
        return groupMapper.getAllGroup(userID);

        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<Group>();
        }

    }

    @Override
    public int getOwner(int groupID) {

        try {
            return groupMapper.getOwner(groupID);

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public Group getGroup(int userID, int contactID) {

        try {
            return groupMapper.getGroup(userID,contactID);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addGroup(int userID, String groupName) {
        Group g = new Group(userID,groupName);
        try {
            if (groupMapper.addGroup(g)>0)
                return g.getId();
            else
                return -1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public boolean renameGroup(int groupID, String name) {

        try {
            if (groupMapper.renameGroup(groupID, name)>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeGroup(int groupID) {
        try {
            if (groupMapper.removeGroup(groupID)>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean groupContact(int userID, int contactID, int groupID) {


        try {
            if (groupMapper.groupContact(userID, contactID, groupID)>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean moveContact(int userID, int contactID, int origin, int target) {
        try {
            if (groupMapper.moveContact(userID, contactID, origin, target)>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
