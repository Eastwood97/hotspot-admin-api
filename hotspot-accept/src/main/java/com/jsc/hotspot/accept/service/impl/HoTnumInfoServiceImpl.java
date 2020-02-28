package com.jsc.hotspot.accept.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.accept.service.HoTnumInfoService;
import com.jsc.hotspot.db.dao.HotNumInfoMapper;
import com.jsc.hotspot.db.dao.ext.HotNumInfoEXTMapper;
import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.domain.HotNumInfoExample;
import com.jsc.hotspot.db.entity.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 08:29
 * @Description: 查询删除操作
 */
@Service
@Slf4j
public class HoTnumInfoServiceImpl implements HoTnumInfoService {
    @Autowired
    private HotNumInfoEXTMapper hotNumInfoEXTMapper;
    @Autowired
    private HotNumInfoMapper hotNumInfoMapper;

    @Override
    public void insertHoTnumInfoNum(HotNumInfo hotNumInfoDAO) {
        hotNumInfoDAO.setCreateTime(LocalDateTime.now());
        hotNumInfoMapper.insert(hotNumInfoDAO);
    }

    @Override
    public List getHoTnumInfoDateNum() {
        List<Map> hoTnumInfoDateList = hotNumInfoEXTMapper.selectCount(returnDate(15), returnDate(0));
        return hoTnumInfoDateList;
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