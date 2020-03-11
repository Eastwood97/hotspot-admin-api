package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotNumDayCount;
import com.jsc.hotspot.db.domain.HotNumDayCountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotNumDayCountExtMapper {
    Integer insertBatch(List<HotNumDayCount> hotNumDayCounts);
}