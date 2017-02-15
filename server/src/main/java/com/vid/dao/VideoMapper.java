package com.vid.dao;

import com.vid.model.Video;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by song on 17-2-12.
 * <p>
 * video相关
 */
@Service
public interface VideoMapper {

    /**
     * 获取用户所有视频
     *
     * @param userID userID，对应id字段
     * @return 视频列表，若无视频，返回size为0的list
     */
    List<Video> getAllVideos(int userID);

    /**
     * 根据videoID得到Video对象
     *
     * @param videoID videoID（对应id）
     * @return Video对象，若不存在，返回null
     */
    Video getVideoByID(int videoID);

    /**
     * 新增视频
     *
     * @param userID    userID，对应id字段
     * @param videoName 视频名称
     * @param size      视频大小
     * @param videoURL  视频url
     * @return videoID
     */
    int insertVideo(int userID, String videoName, long size, String videoURL);

    /**
     * 删除视频
     *
     * @param videoID 视频id
     * @return 删除成功返回true，否则返回false
     */
    boolean removeVideo(int videoID);
}
