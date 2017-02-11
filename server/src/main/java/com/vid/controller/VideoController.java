package com.vid.controller;

import com.vid.config.MsgInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by song on 17-2-11.
 * <p>
 * 视频相关
 */
@Controller
@RequestMapping(value = "/video", method = RequestMethod.POST)
public class VideoController {

    /**
     * 获取所有视频
     *
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public MsgInfo getAllVideos(HttpSession session) {
        return null;
    }

    /**
     * 获取视频二维码
     *
     * @param name 视频名称
     * @return
     */
    @RequestMapping("/code")
    @ResponseBody
    public MsgInfo getQRCode(HttpSession session, String name) {
        return null;
    }

    /**
     * 上传视频
     *
     * @param name     视频名称
     * @param videoURL 视频url
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public MsgInfo uploadVideo(HttpSession session, String name, String videoURL) {
        return null;
    }

    /**
     * 删除视频
     *
     * @param videoName 视频名称
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeVideo(HttpSession session, String videoName) {
        return null;
    }
}
