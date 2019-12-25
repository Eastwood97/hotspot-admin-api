package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.CameraCatInfoService;
import com.jsc.hotspot.db.dao.CameraCatInfoMapper;
import com.jsc.hotspot.db.dao.ext.CameraCatInfoEXTMapper;
import com.jsc.hotspot.db.domain.CameraCatInfo;
import com.jsc.hotspot.db.domain.CameraCatInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("cameraCatInfoServiceImpl")
public class CameraCatInfoServiceImpl implements CameraCatInfoService {

   @Autowired
    private CameraCatInfoMapper cameraCatInfoMapper;
    @Autowired
   private CameraCatInfoEXTMapper cameraCatInfoEXTMapper;



    @Override
    public List<CameraCatInfo> query(Integer page, Integer limit, String startTime,String endTime) {
        CameraCatInfoExample example=new CameraCatInfoExample();
        if(null!=startTime&&!startTime.equals("")&&null!=endTime&&!endTime.equals("")){
            DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime beginTime= LocalDateTime.parse(startTime,df);
            LocalDateTime overTime= LocalDateTime.parse(endTime,df);
            example.or().andCaptureTimeBetween(beginTime,overTime);
        }
       example.setOrderByClause("capture_time desc");

        PageHelper.startPage(page,limit);
        return cameraCatInfoMapper.selectByExampleSelective(example);
    }

    @Override
    public boolean deleteById(String ids) {
        String[] split=ids.split(",");
        int result=cameraCatInfoEXTMapper.deleteById(split);
        return split.length==result;
    }
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
        List<Map> maps1 = cameraCatInfoEXTMapper.selectCount(dateList);
        return maps1;
    }
}