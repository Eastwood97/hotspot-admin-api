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
/**
 * @author tzm
 * @desc 处理摄像头抓拍人脸的相关业务
 */
@Service("cameraCatInfoServiceImpl")
public class CameraCatInfoServiceImpl implements CameraCatInfoService {

   @Autowired
    private CameraCatInfoMapper cameraCatInfoMapper;
    @Autowired
   private CameraCatInfoEXTMapper cameraCatInfoEXTMapper;


    /**
     * 分页查询
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */
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

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteById(String ids) {
        String[] split=ids.split(",");
        int result=cameraCatInfoEXTMapper.deleteById(split);
        return split.length==result;
    }

    /**
     * 查出抓拍数量
     * @return
     */
    //获取人脸总量
    @Override
    public Long getCameraCatInfoList() {
        CameraCatInfoExample cameraCatInfoExample = new CameraCatInfoExample();
        long count = cameraCatInfoMapper.countByExample(cameraCatInfoExample);
        return count;
    }

    /**
     * 以下是魏伟写的方法
     * @return
     */
    //人脸折线图
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

    @Override
    public Map<String, Integer> getFaceByDay() {
        return cameraCatInfoEXTMapper.countByDay();
    }

    @Override
    public Map<String, Integer> getFaceByMonth(){
        return cameraCatInfoEXTMapper.countByMonth();
    }

    @Override
    public Long countCameraCatNums() {
        CameraCatInfoExample cameraCatInfoExample = new CameraCatInfoExample();
        CameraCatInfoExample.Criteria criteria = cameraCatInfoExample.createCriteria();
        return cameraCatInfoMapper.countByExample(cameraCatInfoExample);
    }

    @Override
    public Long currentCameraNums() {
        LocalDateTime localDateTime = LocalDateTime.now();
        CameraCatInfoExample cameraCatInfoExample = new CameraCatInfoExample();
        CameraCatInfoExample.Criteria criteria = cameraCatInfoExample.createCriteria();
        criteria.andCaptureTimeEqualTo(localDateTime);
        return cameraCatInfoMapper.countByExample(cameraCatInfoExample);
    }

}
