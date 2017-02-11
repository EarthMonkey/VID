package com.vid.model;

import java.util.List;

/**
 * Created by song on 17-2-11.
 * <p>
 * 联系人信息
 */
public class ContactProfile {

    /**
     * 姓名（备注）
     */
    private String name;

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

    public ContactProfile(String name, String contactName, String group, String phoneNum,
                          String email, String industry, String interest, List<Video> videoList) {
        this.name = name;
        this.contactName = contactName;
        this.group = group;
        this.phoneNum = phoneNum;
        this.email = email;
        this.industry = industry;
        this.interest = interest;
        this.videoList = videoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
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
