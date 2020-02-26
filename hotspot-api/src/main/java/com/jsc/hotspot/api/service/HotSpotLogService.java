package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.HotspotLog;
import com.jsc.hotspot.db.entity.PageResult;

/**
 * @Auther: WW
 * @Date: 2020/2/21 0021 14:05
 * @Description:
 */
public interface HotSpotLogService {
    //插入日志
    void insertSysLog(HotspotLog sysLog);
    //查询日志
    PageResult selectSysLog(String username, Integer currentPage, Integer pageSize);
    //删除日志
    void deleteSysLog(String id);
}
