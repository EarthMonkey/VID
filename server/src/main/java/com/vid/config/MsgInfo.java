package com.vid.config;

/**
 * Created by song on 17-2-7.
 *
 * 接口调用统一返回消息
 */
public class MsgInfo {

    /**
     * 状态码
     */
    private boolean status;

    /**
     * 状态信息,多为文字说明
     */
    private String info;

    /**
     * 消息附带对象
     */
    private Object object;

    public MsgInfo(boolean status, String info) {
        this(status, info, null);
    }

    public MsgInfo(boolean status, String info, Object object) {
        this.status = status;
        this.info = info;
        this.object = object;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
