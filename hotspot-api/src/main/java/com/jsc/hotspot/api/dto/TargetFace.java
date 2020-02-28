package com.jsc.hotspot.api.dto;


import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author tzm
 * @desc 接受添加人脸的数据载体
 *
 */
@Data
public class TargetFace {

    private Long targetId;

    private String targetName;

    private String desc;

    private String fileId1;

    private String fileId2;

    private String fileId3;


}
