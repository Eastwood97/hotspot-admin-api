package com.jsc.hotspot.accept.service.impl;

import com.jsc.hotspot.accept.service.CameraCatInfoService;
import com.jsc.hotspot.db.dao.ext.CameraCatInfoEXTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("cameraCatInfoServiceImpl")
public class CameraCatInfoServiceImpl implements CameraCatInfoService {
    @Autowired
    private CameraCatInfoEXTMapper cameraCatInfoEXTMapper;

    //人脸折线图
    @Override
    public List getHoTnumInfoDateNum() {
        List<Map> maps1 = cameraCatInfoEXTMapper.selectCount(returnDate(0), returnDate(15));
        return maps1;
    }

    //往前获取时间
    public Date returnDate(Integer index) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -index);
        Date returnDate = c.getTime();
        return returnDate;
    }
}
