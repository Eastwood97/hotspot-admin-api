package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.po.CampareValue;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

public interface HotNumInfoEXTMapper {
    @Select("select * from hot_num_info where create_time>DATE_SUB(#{captureTime},INTERVAL 30 SECOND)")
    List<HotNumInfo> getIntervalNum(LocalDateTime captureTime);
}
