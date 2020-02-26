package com.jsc.hotspot.accept.service;


import com.jsc.hotspot.db.domain.HotCompareResult;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 11:12
 * @Description:
 */

public interface HotCompareResultService {
    //插入黑名单
    void insertHotCompareResult(HotCompareResult hotCompareResult);
}
