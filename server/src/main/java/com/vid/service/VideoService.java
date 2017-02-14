package com.vid.service;

import com.vid.config.MsgInfo;
import com.vid.dao.VideoMapper;
import com.vid.model.Video;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by song on 17-2-12.
 * <p>
 * video相关
 */
@Service
public class VideoService {

    @Resource
    private VideoMapper videoMapper;

    /**
     * 获得用户所有视频
     *
     * @param userID userID
     */
    public MsgInfo getAllVideos(int userID) {
        List<Video> videoList = videoMapper.getAllVideos(userID);

        return new MsgInfo(true, "", videoList);
    }

    /**
     * 上传视频
     *
     * @param userID    userID
     * @param videoName 视频名称
     * @param size      大小
     * @param videoURL  url
     */
    public MsgInfo uploadVideo(int userID, String videoName, long size, String videoURL) {
        int videoID = videoMapper.insertVideo(userID, videoName, size, videoURL);

        return new MsgInfo(true, "上传成功", videoID);
    }

    /**
     * 删除视频
     *
     * @param userID  userID
     * @param videoID 视频id
     */
    public MsgInfo removeVideo(int userID, int videoID) {
        Video video = videoMapper.getVideoByID(videoID);

        if (video == null) {
            return new MsgInfo(false, "视频不存在");
        }

        if (video.getOwnerid() != userID) {
            return new MsgInfo(false, "只能删除自己的视频");
        }

        if (videoMapper.removeVideo(videoID)) {
            return new MsgInfo(true, "删除成功");
        } else {
            return new MsgInfo(false, "删除失败");
        }
    }
}
