package com.jsc.hotspot.accept.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FaceRecognitionInfo {
    private long devId;
    private String targetFaceImg;
    private double compareScore;
    private LocalDateTime captureTime;
    private String captureFaceImg;
    private int libraryName;

}
