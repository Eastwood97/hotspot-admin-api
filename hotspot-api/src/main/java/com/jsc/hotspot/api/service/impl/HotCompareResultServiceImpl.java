package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.HotCompareResultService;
import com.jsc.hotspot.db.dao.HotCompareResultMapper;
import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.domain.HotCompareResultExample;
import com.jsc.hotspot.db.entity.CountList;
import com.jsc.hotspot.db.entity.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 11:12
 * @Description:
 */
@Service
@Slf4j
public class HotCompareResultServiceImpl implements HotCompareResultService {
    @Autowired
    private HotCompareResultMapper hotCompareResultDAOMapper;
    @Autowired
    private HotTargetInfoMapper hotTargetInfoDAOMapper;
    @Override
    public PageResult findHotCompareResult(int page, int row, HotCompareResult hotCompareResultDAO) {
        PageHelper.startPage(page,row);
        HotCompareResultExample hotCompareResultDAOExample = new HotCompareResultExample();
        HotCompareResultExample.Criteria criteria = hotCompareResultDAOExample.createCriteria();
        if(hotCompareResultDAO.getImsi() != null && hotCompareResultDAO.getImsi().length() > 0){
            criteria.andImsiLike("%"+hotCompareResultDAO.getImsi()+"%");
        }
        if(hotCompareResultDAO.getImei() != null && hotCompareResultDAO.getImei().length() > 0){
            criteria.andImeiLike("%"+hotCompareResultDAO.getImei()+"%");
        }
        if(hotCompareResultDAO.getIsdn() != null && hotCompareResultDAO.getIsdn().length() > 0){
            criteria.andIsdnLike("%"+hotCompareResultDAO.getIsdn()+"%");
        }
        if(hotCompareResultDAO.getMode() != null){
            criteria.andModeEqualTo(hotCompareResultDAO.getMode());
        }
        if(hotCompareResultDAO.getCaptureTime() != null){
            criteria.andCaptureTimeBetween( LocalDateTime.of(hotCompareResultDAO.getCaptureTime().toLocalDate(), LocalTime.MIN), LocalDateTime.of(hotCompareResultDAO.getCaptureTime().toLocalDate(), LocalTime.MAX));
        }
        if(hotCompareResultDAO.getFrtDevId() != null){
            criteria.andFrtDevIdEqualTo(hotCompareResultDAO.getFrtDevId());
        }
        if(hotCompareResultDAO.getTargetId() != null){
            criteria.andTargetIdEqualTo(hotCompareResultDAO.getTargetId());
        }
        if(hotCompareResultDAO.getAttribution() != null){
            criteria.andAttributionLike(hotCompareResultDAO.getAttribution());
        }
        Page<HotCompareResult> page1 = (Page<HotCompareResult>) hotCompareResultDAOMapper.selectByExample(hotCompareResultDAOExample);
        return new PageResult(page1.getTotal(),page1.getResult());
    }

    @Override
    public void deleteHotCompareResult(String ids) {
        List<String> strings = JSONArray.parseArray(ids, String.class);
        for (String id : strings) {
            hotCompareResultDAOMapper.deleteByPrimaryKey(Long.decode(id));
        }
    }

    @Override
    public CountList findHotCompareResultCount() {

        return null;
    }

    @Override
    public void insertHotCompareResult(HotCompareResult hotCompareResult) {
        hotCompareResultDAOMapper.insert(hotCompareResult);
    }
}
