package com.jsc.hotspot.common.bean;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author huixing
 * @description 视频下载接口
 * @date 2019/12/27
 */
@Data
public class VideoDownLoadBean {
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private String storeFileName = "D:\\Video";
}
