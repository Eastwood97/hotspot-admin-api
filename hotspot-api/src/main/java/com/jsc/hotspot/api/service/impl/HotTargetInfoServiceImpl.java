package com.jsc.hotspot.api.service.impl;

import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.domain.HotTargetInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: WW
 * @Date: 2019/11/13 0013 10:24
 * @Description:
 */
@Service
@Slf4j
public class HotTargetInfoServiceImpl implements com.jsc.hotspot.api.service.HotTargetInfoService {
    @Autowired
    private HotTargetInfoMapper hotTargetInfoMapper;
    //获取布控总量
    @Override
    public Long getHotTargetInfoNum() {
        HotTargetInfoExample hotTargetInfoExample = new HotTargetInfoExample();
        HotTargetInfoExample.Criteria criteria = hotTargetInfoExample.createCriteria();
        long count = hotTargetInfoMapper.countByExample(hotTargetInfoExample);
        return count;
    }


}
