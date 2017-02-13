package com.vid.model;

import java.util.List;

/**
 * Created by song on 17-2-11.
 * <p>
 * 联系人信息
 */
public class ContactProfile {

    /**
     * 备注姓名
     */
    private String noteName;

    /**
     * 系统中的用户名，若不是系统用户，此项为 ""
     */
    private String contactName;

    /**
     * 分组
     */
    private String group;

    /**
     * 电话号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 行业
     */
    private String industry;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 视频列表
     */
    private List<Video> videoList;

    public ContactProfile() {
        /*do nothing*/
    }

    public ContactProfile(String noteName, String group, User user, List<Video> videoList) {
        this.noteName = noteName;
        this.contactName = user.getUsername();
        this.group = group;
        this.phoneNum = user.getShowtelephone();
        this.email = user.getShowemail();
        this.industry = user.getIndustry();
        this.interest = user.getInterest();
        this.videoList = videoList;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public String toString() {
        return "ContactProfile{" +
                "noteName='" + noteName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", group='" + group + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", industry='" + industry + '\'' +
                ", interest='" + interest + '\'' +
                ", videoList=" + videoList +
                '}';
    }
}
