package com.jsc.hotspot.accept.service.impl;

import com.jsc.hotspot.accept.service.ImsiAreaService;
import com.jsc.hotspot.accept.service.NumAreaService;
import com.jsc.hotspot.db.dao.ImsiAreaMapper;
import com.jsc.hotspot.db.dao.NumAreaMapper;
import com.jsc.hotspot.db.domain.ImsiArea;
import com.jsc.hotspot.db.domain.NumArea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: WW
 * @Date: 2019/11/26 0026 09:21
 * @Description:
 */
@Service
@Slf4j
public class ImsiAreaServiceImpl implements ImsiAreaService {
    @Autowired
    private ImsiAreaMapper imsiAreaMapper;
    @Override
    public void insertImsiArea(ImsiArea imsiArea) {
        imsiAreaMapper.insertImsiArea(imsiArea);
    }

    @Override
    public String selectImsiArea(Integer MCC) {
        return imsiAreaMapper.selectImsiArea(MCC);
    }
}
