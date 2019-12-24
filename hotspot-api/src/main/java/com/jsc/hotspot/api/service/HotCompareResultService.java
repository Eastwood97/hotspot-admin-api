package com.jsc.hotspot.api.service;


import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.entity.CountList;
import com.jsc.hotspot.db.entity.PageResult;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 11:12
 * @Description:
 */

public interface HotCompareResultService {
    PageResult findHotCompareResult(int page, int row, HotCompareResult hotCompareResultDAO);
    void deleteHotCompareResult(String id);
    CountList findHotCompareResultCount();
    void insertHotCompareResult(HotCompareResult hotCompareResult);
}
