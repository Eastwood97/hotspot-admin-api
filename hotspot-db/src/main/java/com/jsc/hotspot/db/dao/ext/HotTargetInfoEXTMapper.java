package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotTargetInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotTargetInfoEXTMapper {
    int deleteNumById(@Param("targetIds") String[] targetIds);
    HotTargetInfo selectHeimingdan(@Param("imsi") String imsi, @Param("imei") String imei);
    int insertForeach(List<HotTargetInfo> list);
}
