package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.HotCompareResultService;
import com.jsc.hotspot.db.dao.HotCompareResultMapper;
import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.dao.ext.HotCompareResultEXTMapper;
import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.domain.HotCompareResultExample;
import com.jsc.hotspot.db.entity.CountList;
import com.jsc.hotspot.db.entity.HotCompareResultList;
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
    @Autowired
    private HotCompareResultEXTMapper hotCompareResultEXTMapper;
    @Override
    /**
     * 功能描述: 获取中标信息
     *
     * @param: page, row, hotCompareResultDAO
     * @return: pageResult
     * @auther: ww
     * @date: 2019/11/7 0007 11:18
     */
    public PageResult findHotCompareResult(int page, int row, HotCompareResultList hotCompareResultList) {
        PageHelper.startPage(page,row);
        List list = hotCompareResultEXTMapper.selectHotCompareResult(hotCompareResultList);
        Page<HotCompareResult> page1 = (Page<HotCompareResult>) list;
        return new PageResult(page1.getTotal(),page1.getResult());
    }
    /**
     * 功能描述: 删除中标信息
     *
     * @param: Long []ids
     * @return: Result
     * @auther: ww
     * @date: 2019/11/7 0007 11:19
     */
    @Override
    public void deleteHotCompareResult(String ids) {
        List<String> strings = JSONArray.parseArray(ids, String.class);
        for (String id : strings) {
            hotCompareResultDAOMapper.deleteByPrimaryKey(Long.decode(id));
        }
    }


}
