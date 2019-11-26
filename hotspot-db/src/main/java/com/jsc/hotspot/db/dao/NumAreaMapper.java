package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.NumArea;

/**
 * @Auther: WW
 * @Date: 2019/11/26 0026 09:16
 * @Description:
 */
public interface NumAreaMapper {
    int insertNumArea(NumArea numArea);
    String selectNumArea(Integer Mobile);
}
