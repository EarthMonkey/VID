package com.vid.model;

/**
 * Created by Jiayiwu on 17/2/9.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Relationship {
    int id;
//    关系拥有方
    int ownerid;
//    关系被拥有方(被名片扫码的那个人)
    int friendid;
//    被分组id
    int groupid;
//    视频id(冗余存储)
    int videoid;
    //备注姓名
    String notename;
//    视频名称(冗余存储)
    String videoname;
//    视频URL(冗余存储)
    String videourl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
