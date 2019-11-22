package com.jsc.hotspot.api.service.impl;

import com.jsc.hotspot.api.service.CameraCatInfoService;
import com.jsc.hotspot.db.dao.CameraCatInfoMapper;
import com.jsc.hotspot.db.domain.CameraCatInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: WW
 * @Date: 2019/11/13 0013 10:14
 * @Description:
 */
@Service
@Slf4j
public class CameraCatInfoServiceImpl implements CameraCatInfoService {
    @Autowired
    private CameraCatInfoMapper cameraCatInfoMapper;
    @Override
    public Long getCameraCatInfoList() {
        CameraCatInfoExample cameraCatInfoExample = new CameraCatInfoExample();
        CameraCatInfoExample.Criteria criteria = cameraCatInfoExample.createCriteria();
        long count = cameraCatInfoMapper.countByExample(cameraCatInfoExample);
        return count;
    }

    @Override
    public List getHoTnumInfoDateNum() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        List<Date> dateList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            c.add(Calendar.DATE, -1);
            Date d = c.getTime();
            dateList.add(d);
        }
        List<Map> maps1 = cameraCatInfoMapper.selectCount(dateList);
        return maps1;
    }
}
