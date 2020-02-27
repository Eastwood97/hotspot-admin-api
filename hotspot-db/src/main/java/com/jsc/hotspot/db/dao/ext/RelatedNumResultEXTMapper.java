package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;

public interface RelatedNumResultEXTMapper {
    int deleteById(@Param("ids") String[] ids);
}
