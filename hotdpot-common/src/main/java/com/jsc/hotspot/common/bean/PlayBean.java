package com.jsc.hotspot.common.bean;

import com.sun.jna.NativeLong;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author huixing
 * @description 播放实体类
 * @date 2020/1/15
 */
@Data
public class PlayBean {
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public int channelId;
}
