package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.FaceCampareResultService;
import com.jsc.hotspot.db.dao.CameraCompareResultMapper;
import com.jsc.hotspot.db.dao.ext.FaceCameraResultEXOMapper;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import com.jsc.hotspot.db.domain.CameraCompareResultExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author tzm
 * @desc 处理人脸识别结果的相关业务
 */
@Service
public class FaceCampareResultServiceImpl implements FaceCampareResultService {

    @Autowired
    private CameraCompareResultMapper cameraCompareResultMapper;
    @Autowired
    private FaceCameraResultEXOMapper faceCameraResultEXOMapper;


    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<CameraCompareResult> query(Integer page, Integer limit, String targetName,String startTime,String endTime) {
        CameraCompareResultExample example=new CameraCompareResultExample();
        CameraCompareResultExample.Criteria criteria =example.createCriteria();
        if(null!=startTime&&!startTime.equals("")&&null!=endTime&&!endTime.equals("")){
            DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime beginTime= LocalDateTime.parse(startTime,df);
            LocalDateTime overTime= LocalDateTime.parse(endTime,df);
            criteria.andCaptureTimeBetween(beginTime,overTime);
        }
        if(null!=targetName&&!targetName.equals("")){
           criteria.andTargetNameLike("%"+targetName+"%");
        }
        example.setOrderByClause("capture_time desc");
        PageHelper.startPage(page,limit);
        return cameraCompareResultMapper.selectByExampleSelective(example);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteById(String ids) {
        String[] split=ids.split(",");
        int result=faceCameraResultEXOMapper.deleteById(split);
        return split.length==result;
    }
}
