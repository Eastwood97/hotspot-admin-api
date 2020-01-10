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
    private HotNumInfoMapper hotNumInfoMapper;

    @Autowired
    private HotNumInfoEXTMapper hotNumInfoEXTMapper;

    @Override
    public PageResult findHotNumInfo(int page, int rows, HotNumInfo hotNumInfoDAO) {
        PageHelper.startPage(page, rows);
        HotNumInfoExample hotNumInfoDAOExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoDAOExample.createCriteria();
//        判断数据
        if (hotNumInfoDAO.getImsi() != null && hotNumInfoDAO.getImsi().length() > 0) {
            criteria.andImsiLike("%" + hotNumInfoDAO.getImsi() + "%");
        }
        if (hotNumInfoDAO.getImei() != null && hotNumInfoDAO.getImei().length() > 0) {
            criteria.andImeiLike("%" + hotNumInfoDAO.getImei() + "%");
        }
        if (hotNumInfoDAO.getIsdn() != null && hotNumInfoDAO.getIsdn().length() > 0) {
            criteria.andIsdnLike("%" + hotNumInfoDAO.getIsdn() + "%");
        }
        if (hotNumInfoDAO.getMode() != null ) {
            criteria.andModeEqualTo(hotNumInfoDAO.getMode());
        }
        if (hotNumInfoDAO.getCaptureTime() != null) {
            criteria.andCaptureTimeEqualTo(hotNumInfoDAO.getCaptureTime());
        }
        if (hotNumInfoDAO.getDevId() != null) {
            criteria.andDevIdEqualTo(hotNumInfoDAO.getDevId());
        }
        if (hotNumInfoDAO.getTargetId() != null) {
            criteria.andTargetIdEqualTo(hotNumInfoDAO.getTargetId());
        }
        if (hotNumInfoDAO.getId() != null) {
            criteria.andIdEqualTo(hotNumInfoDAO.getId());
        }
        Page<HotNumInfo> pages = (Page<HotNumInfo>) hotNumInfoMapper.selectByExample(hotNumInfoDAOExample);
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public void deleteHotNumInfo(String ids) {
        List<String> strings = JSONArray.parseArray(ids, String.class);
        for (String id : strings) {
            hotNumInfoMapper.deleteByPrimaryKey(Long.decode(id));
        }
    }

    @Override
    public Long getHoTnumInfoNum() {
        HotNumInfoExample hotNumInfoExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoExample.createCriteria();
        long count = hotNumInfoMapper.countByExample(hotNumInfoExample);
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
        List<Map> maps1 = null;
        return maps1;
    }

    @Override
    public Long getTodayHoTnumInfoNum() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(now.toLocalDate(), LocalTime.MIN);
        HotNumInfoExample hotNumInfoExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoExample.createCriteria();
        criteria.andCaptureTimeGreaterThan(localDateTime);
        long count = hotNumInfoMapper.countByExample(hotNumInfoExample);
        return count;
    }

    @Override
    public void insertHoTnumInfoNum(HotNumInfo hotNumInfoDAO) {
        hotNumInfoDAO.setCreateTime(LocalDateTime.now());
        hotNumInfoMapper.insert(hotNumInfoDAO);
    }
}