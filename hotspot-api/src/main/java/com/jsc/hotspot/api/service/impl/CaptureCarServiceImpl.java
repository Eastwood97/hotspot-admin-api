package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.CaptureCarService;
import com.jsc.hotspot.db.dao.CaptureCarMapper;
import com.jsc.hotspot.db.dao.ext.CaptureCarEXTMapper;
import com.jsc.hotspot.db.domain.CaptureCar;
import com.jsc.hotspot.db.domain.CaptureCarExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 * @author tzm
 * 处理抓拍车牌数据的相关业务
 */
@Service("CaptureCarService")
public class CaptureCarServiceImpl implements CaptureCarService {

    @Autowired
    private CaptureCarMapper captureCarMapper;

    @Autowired
    private CaptureCarEXTMapper captureCarEXTMapper;

    /**
     * 分页查询抓拍车牌
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<CaptureCar> query(Integer page, Integer limit, String startTime, String endTime,String plateNumber) {
        CaptureCarExample example=new CaptureCarExample();
        if(null!=plateNumber&&!plateNumber.equals("")){
            example.or().andPlateNumberLike(plateNumber+"%");
        }
        if(null!=startTime&&!startTime.equals("")&&null!=endTime&&!endTime.equals("")){
            DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime beginTime= LocalDateTime.parse(startTime,df);
            LocalDateTime overTime= LocalDateTime.parse(endTime,df);
            example.or().andCaptureTimeBetween(beginTime,overTime);
        }
        example.setOrderByClause("capture_time desc");

        PageHelper.startPage(page,limit);

        return captureCarMapper.selectByExampleSelective(example);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteById(String ids) {
        String[] split=ids.split(",");
        int result=captureCarEXTMapper.deleteById(split);
        return split.length==result;
    }
}
