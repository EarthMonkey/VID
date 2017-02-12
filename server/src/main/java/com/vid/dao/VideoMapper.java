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
     * @param username 用户名
     * @return 视频列表，若无视频，返回size为0的list
     */
    List<Video> getAllVideos(String username);

    /**
     * 新增视频
     *
     * @param username  用户名
     * @param videoName 视频名称
     * @param size      视频大小
     * @param videoURL  视频url
     * @return 插入成功返回true，否则返回false
     */
    boolean insertVideo(String username, String videoName, long size, String videoURL);

    /**
     * 删除视频
     *
     * @param username  用户名
     * @param videoName 视频名称
     * @return 删除成功返回true，否则返回false
     */
    boolean removeVideo(String username, String videoName);
}
