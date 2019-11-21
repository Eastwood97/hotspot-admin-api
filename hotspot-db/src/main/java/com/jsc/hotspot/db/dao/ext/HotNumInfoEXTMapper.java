package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.po.CampareValue;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HotNumInfoEXTMapper {
    List<HotNumInfo> getIntervalNum();
}
