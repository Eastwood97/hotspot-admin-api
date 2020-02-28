package com.jsc.hotspot.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tzm
 * @desc 接受kafka人脸识别信息的数据载体
 */
@Data
public class FaceRecognitionInfoDTO {
    private String targetName;
    private long devId;
    private String devIp;
    private String targetFaceImg;
    private Double compareScore;
    private LocalDateTime captureTime;
    private String captureFaceImg;
    private String libraryName;
    private String sceneImg;
    private String targetLibrary;
    private String sceneStorageUrl;
    private String targetFaceStorageUrl;
    private String captureFaceStorageUrl;
}
