package com.vid.controller;

import com.vid.config.MsgInfo;
import com.vid.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 获取视频信息，用户未登录
     *
     * @param videoID 视频ID
     * @return 包括：
     * 1. {"status":true,"info":"","object":Video}
     * 2. {"status":false,"info":"视频不存在","object":null}
     */
    @RequestMapping("/info/1")
    @ResponseBody
    public MsgInfo getVideoInfo(@RequestParam int videoID) {
        return videoService.getVideoInfo(videoID);
    }

    /**
     * 获取视频信息，用户已登录
     *
     * @param videoID 视频ID
     * @return 包括：
     * 1. {"status":true,"info":noteName,"object":Video}
     * 2. {"status":true,"info":"非联系人","object":Video}
     * 3. {"status":false,"info":"视频不存在","object":null}
     */
    @RequestMapping("/info/2")
    @ResponseBody
    public MsgInfo getVideoInfo(HttpSession session, @RequestParam int videoID) {
        int userID = (Integer) session.getAttribute("userID");

        return videoService.getVideoInfo(userID, videoID);
    }

    /**
     * 获取所有视频
     *
     * @return {"status":true,"info":"","object":[Video]}
     */
    @RequestMapping("/all")
    @ResponseBody
    public MsgInfo getAllVideos(HttpSession session) {
        int userID = (Integer) session.getAttribute("userID");

        return videoService.getAllVideos(userID);
    }

    /**
     * 上传视频
     *
     * @param name 视频名称
     * @param url  视频url
     * @return {"status":true,"info":"上传成功","object":videoID}
     */
    @RequestMapping("/upload")
    @ResponseBody
    public MsgInfo uploadVideo(HttpSession session, @RequestParam String name, long size, @RequestParam String url) {
        int userID = (Integer) session.getAttribute("userID");

        return videoService.uploadVideo(userID, name, size, url);
    }

    /**
     * 删除视频
     *
     * @param videoID videoID
     * @return 包括：
     * 1. {"status":true,"info":"删除成功","object":null}
     * 2. {"status":false,"info":"视频不存在","object":null}
     * 3. {"status":false,"info":"只能删除自己的视频","object":null}
     */
    @RequestMapping("/remove")
    @ResponseBody
    public MsgInfo removeVideo(HttpSession session, @RequestParam int videoID) {
        int userID = (Integer) session.getAttribute("userID");

        return videoService.removeVideo(userID, videoID);
    }



}
