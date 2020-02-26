package com.jsc.hotspot.accept.service;

import com.jsc.hotspot.db.domain.NumArea;


/**
 * @Auther: WW
 * @Date: 2019/11/26 0026 09:21
 * @Description:
 */

public interface NumAreaService {
    //查询国外的国籍
    NumArea selectNumArea(String Mobile);
}
