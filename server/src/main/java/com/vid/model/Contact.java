package com.vid.model;

/**
 * Created by song on 17-2-11.
 * <p>
 * 联系人
 */
public class Contact {

    /**
     * 联系人姓名（备注）
     */
    private String noteName;

    /**
     * 联系人分组
     */
    private String group;

    /**
     * userID
     */
    private int userID;

    public Contact() {
        /*do nothing*/
    }

    public Contact(String noteName, String group, int userID) {
        this.noteName = noteName;
        this.group = group;
        this.userID = userID;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "noteName='" + noteName + '\'' +
                ", group='" + group + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
