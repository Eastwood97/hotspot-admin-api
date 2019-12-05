package com.jsc.hotspot.accept.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FaceRecognitionInfo {
    private String targetName;
    private long devId;
    private String devIp;
    private String targetFaceImg;
    private double compareScore;
    private LocalDateTime captureTime;
    private String captureFaceImg;
    private int libraryName;
    private String sceneImg;
    private String targetLibrary;
    private String sceneStorageUrl;
    private String targetFaceStorageUrl;
    private String captureFaceStorageUrl;
}
