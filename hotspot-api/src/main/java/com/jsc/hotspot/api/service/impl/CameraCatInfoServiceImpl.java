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
import java.util.List;

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
}
