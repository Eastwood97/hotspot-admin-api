package com.jsc.hotspot.common.data;

import com.jsc.hotspot.common.database.domain.MstTaskFlowConf;
import com.jsc.hotspot.common.msg.record.MsgRecordDepot;
import lombok.Data;

import java.util.List;

/**
 * 任务信息
 */
@Data
public class TaskProcessParam implements MsgRecordDepot {

    private String taskId;
    private Integer subNum;
    private String commonParam;
    private String firstParam;
    private List<MstTaskFlowConf> procFlowConfs;
}
