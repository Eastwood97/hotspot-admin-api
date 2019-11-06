package com.jsc.hotspot.common.data;

import com.jsc.hotspot.common.database.domain.WhsBodyComp;
import lombok.Data;

import java.util.Map;

/**
 * 人体比对结果数据查询结果记录
 */
@Data
public class InquiryBodyCompResultRecord extends WhsBodyComp {

    private Map<String, Object> attrs;
    private String fullImgId;
}
