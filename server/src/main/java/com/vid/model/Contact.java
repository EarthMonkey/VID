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
    private String name;

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

    public Contact(String name, String group, String username) {
        this.name = name;
        this.group = group;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
