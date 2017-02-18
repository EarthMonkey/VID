package com.vid.model;

/**
 * Created by Jiayiwu on 17/2/9.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Group {

    int id;
    int userid;
//    分组名称
    String name;


    public Group() {
    }


    public Group(int userid, String name){
        this.userid = userid;
        this.name = name;
    }

    public Group(int id, int userid, String name) {
        this.id = id;
        this.userid = userid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
