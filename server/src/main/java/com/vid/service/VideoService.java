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

    public MsgInfo getAllVideos(String username) {
        List<Video> videoList = videoMapper.getAllVideos(username);

        return new MsgInfo(true, "", videoList);
    }

    public MsgInfo uploadVideo(String username, String videoName, long size, String videoURL) {
        if (isOwner(username, videoName)) {
            return new MsgInfo(false, "已存在同名视频");
        }

        if (videoMapper.insertVideo(username, videoName, size, videoURL)) {
            return new MsgInfo(true, "上传成功");
        } else {
            return new MsgInfo(false, "上传失败");
        }
    }

    public MsgInfo removeVideo(String username, String videoName) {
        if (!isOwner(username, videoName)) {
            return new MsgInfo(false, "视频不存在");
        }

        if (videoMapper.removeVideo(username, videoName)) {
            return new MsgInfo(true, "删除成功");
        } else {
            return new MsgInfo(false, "删除失败");
        }
    }

    /**
     * 判断某视频是否属于用户
     *
     * @param username  用户名
     * @param videoName 视频名
     * @return 若属于，返回true
     */
    private boolean isOwner(String username, String videoName) {
        List<Video> videoList = videoMapper.getAllVideos(username);

        for (Video video : videoList) {
            if (video.getName().equals(videoName)) {
                return true;
            }
        }

        return false;
    }
}
