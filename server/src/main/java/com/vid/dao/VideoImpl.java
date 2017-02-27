package com.vid.dao;

import com.vid.mapper.VideoMapper;
import com.vid.model.Video;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
public class VideoImpl implements VideoDao {

    @Resource
    VideoMapper videoMapper;

    @Override
    public List<Video> getAllVideos(int userID) {
        try {
            return videoMapper.getAllVideos(userID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Video getVideoByID(int videoID) {
        try {
            return videoMapper.getVideoByID(videoID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertVideo(int userID, String videoName, long size, String videoURL) {
        try {
            Video video = new Video(userID, videoName, size, videoURL);
            if (videoMapper.insertVideo(video) > 0)
                return video.getId();
            else
                return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean removeVideo(int videoID) {
        if (videoMapper.removeVideo(videoID) > 0)
            return true;
        return false;
    }
}
