package com.jsc.hotspot.accept.service;

import com.jsc.hotspot.db.domain.HotTargetInfo;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/11/13 0013 10:23
 * @Description:
 */
public interface HotTargetInfoService {
    //查询黑名单
    HotTargetInfo selectHotTargetInfoList(String imsi, String imei);
}
