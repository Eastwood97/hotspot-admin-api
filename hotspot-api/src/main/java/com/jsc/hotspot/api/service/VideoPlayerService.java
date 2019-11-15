package com.jsc.hotspot.api.service;

import com.jsc.hotspot.common.biz.BizResult;

import java.util.List;

/**
 * Created by huixing on 2019/11/10.
 */
public interface VideoPlayerService {

    /**
     * 获取数据库中存储的摄像头的RTSP路径
     * @return
     */
    BizResult<List<String>> getVideoRTSPUrl();

}
