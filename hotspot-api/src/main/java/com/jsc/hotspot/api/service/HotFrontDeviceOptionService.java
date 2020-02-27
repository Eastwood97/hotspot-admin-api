package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.HotFrontDeviceOption;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/12/23 0023 16:16
 * @Description:
 */
public interface HotFrontDeviceOptionService {
    //归属、人流查询获取设备信息
    List<HotFrontDeviceOption> getHotFrontDeviceOption(Integer id);
}
