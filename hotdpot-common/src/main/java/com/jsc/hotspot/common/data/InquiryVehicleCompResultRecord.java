package com.jsc.hotspot.common.data;

import com.jsc.hotspot.common.database.domain.WhsVehComp;
import lombok.Data;

import java.util.Map;

/**
 * 车辆比对结果数据查询结果记录
 */
@Data
public class InquiryVehicleCompResultRecord extends WhsVehComp {

    private Map<String, Object> attrs;
    private String fullImgId;
}
