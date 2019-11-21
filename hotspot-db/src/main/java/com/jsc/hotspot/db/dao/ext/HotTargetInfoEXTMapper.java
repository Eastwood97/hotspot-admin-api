package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//@Mapper
public interface HotTargetInfoEXTMapper {
    int deleteNumById(@Param("targetIds") String [] targetIds);
}
