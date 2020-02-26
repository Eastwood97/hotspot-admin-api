package com.jsc.hotspot.accept.service.impl;

import com.jsc.hotspot.accept.service.HotTargetInfoService;
import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.dao.ext.HotTargetInfoEXTMapper;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import com.jsc.hotspot.db.domain.HotTargetInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/11/13 0013 10:24
 * @Description:
 */
@Service
@Slf4j
public class HotTargetInfoServiceImpl implements HotTargetInfoService {
    @Autowired
    private HotTargetInfoEXTMapper hotTargetInfoEXTMapper;


    @Override
    public HotTargetInfo selectHotTargetInfoList(String imsi, String imei) {
        HotTargetInfo list = hotTargetInfoEXTMapper.selectHeimingdan(imsi,imei);
        return list;
    }
}
