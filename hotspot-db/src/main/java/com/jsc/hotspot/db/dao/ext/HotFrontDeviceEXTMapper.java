package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface HotFrontDeviceEXTMapper {

    int deleteById(@Param("devIds") String [] devIds);
}
