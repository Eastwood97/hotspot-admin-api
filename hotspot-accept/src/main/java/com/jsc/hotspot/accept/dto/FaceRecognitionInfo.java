package com.jsc.hotspot.accept.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class FaceRecognitionInfo {
    private String targetName;
    private long devId;
    private String devIp;
    private String targetFaceImg;
    private double compareScore;
    private String captureTime;
    private String captureFaceImg;
    private int libraryName;
    private String sceneImg;
    private String targetLibrary;
    private String sceneStorageUrl;
    private String targetFaceStorageUrl;
    private String captureFaceStorageUrl;
}
