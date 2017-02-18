package com.vid.mapper;

import com.vid.model.Contact;
import com.vid.model.Relationship;
import com.vid.model.User;
import com.vid.model.Video;

import java.util.List;

/**
 * Created by Jiayiwu on 17/2/17.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface ContactsMapper {

    List<Contact> getAllContacts(int userID);

    Relationship isContacts(int userID, int contactID);

    String getNoteName(int userID, int contactID);

    List<Video> getAllVideos(int userID, int contactID);

    int addContact(int userID, String noteName, int friendid,String telephone,String interest,String email,String industy);

    int addContactWithVideo(int userID, String noteName, int friendid,String telephone,String interest,String email,String industy,int videoid);

    int editContactProfile(int userID, int contactID, String noteName, String phoneNum,
                           String email, String industry, String interest);

    int removeContact(int userID, int contactID);
}
