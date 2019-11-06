package com.jsc.hotspot.common.msg.record;

import com.jsc.hotspot.common.data.ObjectPositionInfo;
import lombok.Data;

import java.util.List;

/**
 * 抓拍原图结果
 */
@Data
public class MsgRecordFull extends MsgRecordDepotCommon {

    private String imgId;
    private List<ObjectPositionInfo> objPos;
}
