package com.vid.model;

import java.util.List;

/**
 * Created by song on 17-2-11.
 *
 * 用户个人信息，用于展示
 */
public class Profile {

    private int userID;

    private String name;

    private String phoneNum;

    private String email;

    private String industry;

    private String interest;

    private String portrait;

    private List<Video> videoList;

    public Profile() {
        /*do nothing*/
    }

    public Profile(User user, List<Video> videoList) {
        this.userID = user.getId();
        this.name = user.getName();
        this.phoneNum = user.getShowtelephone();
        this.email = user.getShowemail();
        this.industry = user.getIndustry();
        this.interest = user.getInterest();
        this.portrait = user.getImgpath();
        this.videoList = videoList;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", industry='" + industry + '\'' +
                ", interest='" + interest + '\'' +
                '}';
    }
}
