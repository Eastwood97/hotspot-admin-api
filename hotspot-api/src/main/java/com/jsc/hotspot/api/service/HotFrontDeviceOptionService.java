package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.HotFrontDeviceOption;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/12/23 0023 16:16
 * @Description:
 */
public interface HotFrontDeviceOptionService {
    List<HotFrontDeviceOption> getHotFrontDeviceOption(Integer id);
}
