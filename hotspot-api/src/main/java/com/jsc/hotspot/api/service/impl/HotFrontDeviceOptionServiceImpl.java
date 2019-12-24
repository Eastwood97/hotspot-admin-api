package com.jsc.hotspot.api.service.impl;

import com.jsc.hotspot.api.service.HotFrontDeviceOptionService;
import com.jsc.hotspot.db.dao.HotFrontDeviceOptionMapper;
import com.jsc.hotspot.db.domain.HotFrontDeviceOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/12/23 0023 16:17
 * @Description:
 */
@Service
@Transactional
public class HotFrontDeviceOptionServiceImpl implements HotFrontDeviceOptionService {
    @Autowired
    private HotFrontDeviceOptionMapper hotFrontDeviceOptionMapper;
    @Override
    public List<HotFrontDeviceOption> getHotFrontDeviceOption(Integer id) {
        return hotFrontDeviceOptionMapper.selectHotFrontDeviceOption(id);
    }
}
