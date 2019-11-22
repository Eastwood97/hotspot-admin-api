package com.jsc.hotspot.accept.service;

import com.jsc.hotspot.db.domain.HotTargetInfo;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/11/13 0013 10:23
 * @Description:
 */
public interface HotTargetInfoService {
    Long getHotTargetInfoNum();
    List<HotTargetInfo> selectHotTargetInfoList(String imsi, String imei);
}
