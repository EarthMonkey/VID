package com.vid.model;

/**
 * Created by Jiayiwu on 17/2/9.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Video {
    int id;
//    视频拥有者ID
    int ownerid;
//    视频名称
    String name;
//    视频大小
    long size;
//    七牛返回的视频地址
    String url;

    public Video() {
    }

    public Video(int ownerid, String name, long size, String url) {
        this.ownerid = ownerid;
        this.name = name;
        this.size = size;
        this.url = url;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
