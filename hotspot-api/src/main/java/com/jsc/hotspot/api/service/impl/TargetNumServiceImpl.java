package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.TargetNumService;
import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import com.jsc.hotspot.db.domain.HotTargetInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("TargetNumService")
public class TargetNumServiceImpl implements TargetNumService {

    @Autowired
    private HotTargetInfoMapper hotTargetInfoMapper;
    private HotTargetInfo.Column[] columns=new HotTargetInfo.Column[]{
            HotTargetInfo.Column.targetId, HotTargetInfo.Column.targetName, HotTargetInfo.Column.imsi,
            HotTargetInfo.Column.imei,HotTargetInfo.Column.isdn, HotTargetInfo.Column.createTime,HotTargetInfo.Column.updateTime,
            HotTargetInfo.Column.desc,HotTargetInfo.Column.operatorId
    };

    @Override
    public List<HotTargetInfo> query(Integer page, Integer limit,String targetName,String imsi,String imei,String isdn ) {
        HotTargetInfoExample example=new HotTargetInfoExample();
        HotTargetInfoExample.Criteria criteria=example.createCriteria();
        if (!StringUtils.isEmpty(targetName)) {
            criteria.andTargetNameLike(targetName);
        }
        if (!StringUtils.isEmpty(imsi)) {
            criteria.andImsiEqualTo(imsi);
        }
        if (!StringUtils.isEmpty(imei)) {
            criteria.andImsiEqualTo(imei);
        }
        if (!StringUtils.isEmpty(isdn)) {
            criteria.andImsiEqualTo(isdn);
        }

       example.setOrderByClause("create_time desc");
        PageHelper.startPage(page, limit);
        return hotTargetInfoMapper.selectByExample(example);
    }

    @Override
    public int updateById() {
        return 0;
    }

    @Override
    public void deleteById() {

    }

    @Override
    public void add() {

    }
}
