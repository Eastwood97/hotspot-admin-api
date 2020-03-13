package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.inject.internal.util.$Nullable;
import com.jsc.hotspot.api.service.RegionService;
import com.jsc.hotspot.db.dao.RegionMapper;
import com.jsc.hotspot.db.dao.ext.RegionEXTMapper;
import com.jsc.hotspot.db.domain.HotTargetInfoExample;
import com.jsc.hotspot.db.domain.Region;
import com.jsc.hotspot.db.domain.RegionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service("regionService")
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private RegionEXTMapper regionEXTMapper;

    @Override
    public List<Region> query(Integer page, Integer limit, String regionName, Integer state) {
       RegionExample example=new RegionExample();
       RegionExample.Criteria criteria=example.createCriteria();
        if (!StringUtils.isEmpty(regionName))
            criteria.andRegionNameLike(regionName+"%");

        if(state!=null)
            criteria.andStateEqualTo(state);
        example.setOrderByClause("update_time desc");
        PageHelper.startPage(page, limit);
        return regionMapper.selectByExampleSelective(example);
    }

    @Override
    public List<Region> selectAll() {
        return regionEXTMapper.selectAll();
    }

    @Override
    public boolean deleteById(String ids) {
        String[] split=ids.split(",");
        int result=regionEXTMapper.deleteById(split);
        return split.length==result;
    }

    @Override
    public void add(Region region) {
        region.setUpdateTime(LocalDateTime.now());
       regionMapper.insertSelective(region);
    }

    @Override
    public int updateById(Region region) {
        region.setUpdateTime(LocalDateTime.now());
        return regionMapper.updateByPrimaryKeySelective(region);
    }

    @Override
    public long getRegionNameCount(String regionName) {
        RegionExample example=new RegionExample();
        example.or().andRegionNameEqualTo(regionName);
        return regionMapper.countByExample(example);
    }
}
