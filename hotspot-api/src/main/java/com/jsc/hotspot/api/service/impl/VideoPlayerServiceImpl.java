package com.jsc.hotspot.api.service.impl;

import com.google.common.collect.Lists;
import com.jsc.hotspot.api.service.VideoPlayerService;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.enums.DeviceTypeEnum;
import com.jsc.hotspot.db.dao.HotFrontDeviceMapper;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import com.jsc.hotspot.db.domain.HotFrontDeviceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2019/11/10
 */
@Service
public class VideoPlayerServiceImpl implements VideoPlayerService{

    @Autowired
    private HotFrontDeviceMapper hotFrontDeviceMapper;

    @Override
    public BizResult<List<String>> getVideoRTSPUrl() {
        HotFrontDeviceExample hotFrontDeviceExample = new HotFrontDeviceExample();
        HotFrontDeviceExample.Criteria criteria = hotFrontDeviceExample.createCriteria();
        criteria.andDevTypeEqualTo(DeviceTypeEnum.CAMERA.getCode());
        criteria.andDevTypeEqualTo(DeviceTypeEnum.ZNCAMERA.getCode());
        List<HotFrontDevice> hotFrontDevices = hotFrontDeviceMapper.selectByExampleSelective(hotFrontDeviceExample, HotFrontDevice.Column.ipAddr);
        List<String> list = new ArrayList<>();
        hotFrontDevices.forEach((hotFrontDevice -> {
            list.add(hotFrontDevice.getIpAddr());
        }));
        return BizResult.create(list);
    }
}
