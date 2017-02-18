package com.vid.model;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

public class User {
    private  int id;

    //用户昵称
    private String name;
//    密码
    private String password;
//    用户头像保存位置
    private String imgpath;
//    注册时绑定的邮箱,如果用手机注册,则该字段为空.如用邮箱注册,则绑定邮箱和显示邮箱一致,但绑定邮箱不能更改,显示邮箱可以在个人设置中更改
    private String bindingemail;
//    名片对外展示的邮箱
    private String showemail;
//    同绑定邮箱规则
    private String bindingtelephone;
//    名片对外展示的电话
    private String showtelephone;
//    行业
    private String industry;
//    兴趣
    private String interest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getBindingemail() {
        return bindingemail;
    }

    public void setBindingemail(String bindingemail) {
        this.bindingemail = bindingemail;
    }

    public String getShowemail() {
        return showemail;
    }

    public void setShowemail(String showemail) {
        this.showemail = showemail;
    }

    public String getBindingtelephone() {
        return bindingtelephone;
    }

    public void setBindingtelephone(String bindingtelephone) {
        this.bindingtelephone = bindingtelephone;
    }

    public String getShowtelephone() {
        return showtelephone;
    }

    public void setShowtelephone(String showtelephone) {
        this.showtelephone = showtelephone;
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

    public User() {
        /*do nothing*/
    }

    /**
     * @param phoneNum 可能为null
     */
    public User(String email, String phoneNum, String password, String name) {
        this.bindingemail = email;
        this.showemail = email;
        this.bindingtelephone = phoneNum;
        this.showtelephone = phoneNum;
        this.password = password;
        this.name = name;
    }
}
