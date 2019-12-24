package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;

public interface FaceCameraResultEXOMapper {
    int deleteById(@Param("ids") String [] ids);
}
