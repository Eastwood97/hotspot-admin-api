package com.jsc.hotspot.accept.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.accept.service.HotCompareResultService;
import com.jsc.hotspot.db.dao.HotCompareResultMapper;
import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.domain.HotCompareResultExample;
import com.jsc.hotspot.db.entity.CountList;
import com.jsc.hotspot.db.entity.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public void insertHotCompareResult(HotCompareResult hotCompareResult) {
        hotCompareResultDAOMapper.insert(hotCompareResult);
    }
}
