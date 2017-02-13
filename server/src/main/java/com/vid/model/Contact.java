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
     * 联系人系统用户名，若不是系统用户，此项为 ""
     */
    private String username;

    public Contact() {
        /*do nothing*/
    }

    public Contact(String noteName, String group, String username) {
        this.noteName = noteName;
        this.group = group;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "noteName='" + noteName + '\'' +
                ", group='" + group + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
