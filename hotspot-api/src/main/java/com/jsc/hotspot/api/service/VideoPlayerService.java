package com.jsc.hotspot.api.service;

import com.jsc.hotspot.api.dto.RecordVideoDTO;
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

    /**
     * 下載視頻到本地
     * @param recordVideoDTO
     * @return
     */
    BizResult<List<String>> getDownLoadVideo(RecordVideoDTO recordVideoDTO);

}
