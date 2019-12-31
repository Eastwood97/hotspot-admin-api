package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotTargetInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotTargetInfoEXTMapper {
    int deleteNumById(@Param("targetIds") String [] targetIds);

    int insertForeach(List<HotTargetInfo> list);
}
