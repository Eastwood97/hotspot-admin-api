package com.jsc.hotspot.accept.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

/**
 * @author huixing
 * @description 接受摄像机传过来的数据
 * @date 2019/10/29
 */
@Data
public class AcceptBean {

    private Long device_id;
    private String LocalLibPicName;
    private int Facelibrary;
    private Double CompareScore;
    private LocalDateTime SnapTime;
    private String catchFace;
}
