package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;

public interface CameraCatInfoEXTMapper {
    int deleteById(@Param("ids") String [] ids);
}
