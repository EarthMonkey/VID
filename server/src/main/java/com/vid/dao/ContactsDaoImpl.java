package com.vid.dao;

import com.vid.model.Contact;
import com.vid.model.User;
import com.vid.model.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
public class ContactsDaoImpl implements ContactsDao {
    @Override
    public List<Contact> getAllContacts(int userID) {
        return null;
    }

    @Override
    public boolean isContacts(int userID, int contactID) {
        return false;
    }

    @Override
    public String getNoteName(int userID, int contactID) {
        return null;
    }

    @Override
    public List<Video> getAllVideos(int userID, int contactID) {
        return null;
    }

    @Override
    public boolean addContact(int userID, String noteName, User user) {
        return false;
    }

    @Override
    public boolean addContactWithVideo(int userID, int contactID, String noteName, int videoID) {
        return false;
    }

    @Override
    public boolean editContactProfile(int userID, int contactID, String noteName, String phoneNum, String email, String industry, String interest) {
        return false;
    }

    @Override
    public boolean removeContact(int userID, int contactID) {
        return false;
    }
}
