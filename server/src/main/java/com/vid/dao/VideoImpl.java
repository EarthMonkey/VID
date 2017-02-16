package com.vid.dao;

import com.vid.model.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
@Repository
public class VideoImpl implements VideoDao {
    @Override
    public List<Video> getAllVideos(int userID) {
        return null;
    }

    @Override
    public Video getVideoByID(int videoID) {
        return null;
    }

    @Override
    public int insertVideo(int userID, String videoName, long size, String videoURL) {
        return 0;
    }

    @Override
    public boolean removeVideo(int videoID) {
        return false;
    }
}
