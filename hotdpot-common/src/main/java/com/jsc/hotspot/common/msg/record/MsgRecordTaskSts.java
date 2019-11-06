package com.jsc.hotspot.common.msg.record;

import com.jsc.hotspot.common.database.domain.MstTaskSts;
import lombok.Data;

/**
 * 任务状态数据消息记录
 */
@Data
public class MsgRecordTaskSts extends MstTaskSts implements MsgRecordDepot {

}
