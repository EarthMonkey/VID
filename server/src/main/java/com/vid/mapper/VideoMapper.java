package com.vid.mapper;

import com.vid.model.User;
import com.vid.model.Video;

import java.util.List;

/**
 * Created by Jiayiwu on 17/2/16.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public interface VideoMapper {

    public List<Video> getAllVideos(int userID);

    public Video getVideoByID(int videoID);

    public int insertVideo(Video video);

    public int removeVideo(int videoID);
}
