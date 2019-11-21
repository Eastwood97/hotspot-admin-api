package com.jsc.hotspot.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author huixing
 * @description
 * @date 2019/11/20
 */
@Data
public class RecordSearchDTO {
    private String startTime;
    private String endTime;
    private String devId;
}
