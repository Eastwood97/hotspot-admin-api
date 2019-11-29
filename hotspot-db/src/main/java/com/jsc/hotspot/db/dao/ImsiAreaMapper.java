package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.ImsiArea;

/**
 * @Auther: WW
 * @Date: 2019/11/26 0026 14:42
 * @Description:
 */
public interface ImsiAreaMapper {
    int insertImsiArea(ImsiArea imsiArea);
    String selectImsiArea(Integer MCC);
}
