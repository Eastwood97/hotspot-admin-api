package com.jsc.hotspot.api.service;


import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.entity.CountList;
import com.jsc.hotspot.db.entity.HotCompareResultList;
import com.jsc.hotspot.db.entity.PageResult;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 11:12
 * @Description:
 */

public interface HotCompareResultService {
    //查询
    PageResult findHotCompareResult(int page, int row, HotCompareResultList hotCompareResultDAO,
                                    String startTime,
                                    String endTime);
    /**
     * 功能描述: 删除中标信息
     *
     * @param: Long []ids
     * @return: Result
     * @auther: ww
     * @date: 2019/11/7 0007 11:19
     */
    void deleteHotCompareResult(String id);
}
