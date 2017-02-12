package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by song on 17-2-11.
 * <p>
 * 视频相关
 */
@Controller
@RequestMapping(value = "/video", method = RequestMethod.POST)
public class VideoController {

    @Resource
    private VideoService videoService;

    /**
     * 获取所有视频
     *
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public MsgInfo getAllVideos(HttpSession session) {
        String username = (String) session.getAttribute("username");

        return videoService.getAllVideos(username);
    }

    /**
     * 上传视频
     *
     * @param name 视频名称
     * @param url  视频url
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public MsgInfo uploadVideo(HttpSession session, String name, long size, String url) {
        String username = (String) session.getAttribute("username");

        return videoService.uploadVideo(username, name, size, url);
    }

    /**
     * 删除视频
     *
     * @param name 视频名称
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeVideo(HttpSession session, String name) {
        String username = (String) session.getAttribute("username");

        return videoService.removeVideo(username, name);
    }
}
