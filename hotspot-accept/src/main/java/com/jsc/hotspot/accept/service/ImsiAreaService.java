package com.jsc.hotspot.accept.service;

import com.jsc.hotspot.db.domain.ImsiArea;
import com.jsc.hotspot.db.domain.NumArea;


/**
 * @Auther: WW
 * @Date: 2019/11/26 0026 09:21
 * @Description:
 */

public interface ImsiAreaService {
    //查询IMSI区域
    String selectImsiArea(Integer MCC);
}
