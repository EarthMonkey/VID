package com.vid.model;

/**
 * Created by song on 17-2-11.
 *
 * 用户个人信息，用于展示
 */
public class Profile {

    private String userName;

    private String name;

    private String phoneNum;

    private String email;

    public Profile() {
        /*do nothing*/
    }

    public Profile(User user) {
        this.userName = user.getUsername();
        this.name = user.getName();
        this.phoneNum = user.getShowtelephone();
        this.email = user.getShowemail();
    }

    public Profile(String userName, String name, String phoneNum, String email) {
        this.userName = userName;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
