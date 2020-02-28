package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.PlateNumberCompareService;
import com.jsc.hotspot.db.dao.PlateNumberCompareMapper;
import com.jsc.hotspot.db.dao.ext.PlateNumberCompareEXTMapper;
import com.jsc.hotspot.db.domain.PlateNumberCompare;
import com.jsc.hotspot.db.domain.PlateNumberCompareExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author tzm
 * @desc 处理车牌比对与关联的相关业务
 */
@Service("PlateNumberCompareService")
public class PlateNumberCompareServiceImpl implements PlateNumberCompareService {

    @Autowired
    private PlateNumberCompareMapper plateNumberCompareMapper;

    @Autowired
    private PlateNumberCompareEXTMapper plateNumberCompareEXTMapper;

    /**
     * 分页查询数据
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @param plateNumber
     * @return
     */
    @Override
    public List<PlateNumberCompare> query(Integer page, Integer limit, String startTime, String endTime, String plateNumber) {
        PlateNumberCompareExample example=new PlateNumberCompareExample();
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

        return plateNumberCompareMapper.selectByExampleSelective(example);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteById(String ids) {
        String[] split=ids.split(",");
        int result=plateNumberCompareEXTMapper.deleteById(split);
        return split.length==result;
    }
}
