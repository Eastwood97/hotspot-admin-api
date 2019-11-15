package com.jsc.hotspot.api.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class TargetFace {

    private Long targetId;

    private String targetName;

    private String desc;

    private String fileId1;

    private String fileId2;

    private String fileId3;


}
