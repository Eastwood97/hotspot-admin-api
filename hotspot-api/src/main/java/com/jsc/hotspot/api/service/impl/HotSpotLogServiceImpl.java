package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsc.hotspot.api.service.HotSpotLogService;
import com.jsc.hotspot.db.dao.HotspotLogMapper;
import com.jsc.hotspot.db.domain.HotspotLog;
import com.jsc.hotspot.db.domain.HotspotLogExample;
import com.jsc.hotspot.db.entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HotSpotLogServiceImpl implements HotSpotLogService {
    @Autowired
    private HotspotLogMapper hotspotLogMapper;

    @Override
    public void insertSysLog(HotspotLog hotspotLog) {
        hotspotLogMapper.insert(hotspotLog);
    }
    /**
     *
     * 功能描述: 获取日志分页数据
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2020/2/21 0021 14:18
     */
    @Override
    public PageResult selectSysLog(String username, Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        HotspotLogExample hotspotLogExample = new HotspotLogExample();
        HotspotLogExample.Criteria criteria = hotspotLogExample.createCriteria();
        if (username != null && !"".equals(username)) {
            criteria.andUsernameEqualTo(username);
        }
        hotspotLogExample.setOrderByClause("id desc");
        List<HotspotLog> hotspotLogs = hotspotLogMapper.selectByExample(hotspotLogExample);
        PageInfo<HotspotLog> page = new PageInfo<>(hotspotLogs);
        return new PageResult(page.getTotal(), page.getList());
    }
    //删除日志
    @Override
    public void deleteSysLog(String ids) {
        List<Long> strings = JSONArray.parseArray(ids, Long.class);
        for (Long id : strings) {
            hotspotLogMapper.deleteByPrimaryKey(id);
        }
    }
}