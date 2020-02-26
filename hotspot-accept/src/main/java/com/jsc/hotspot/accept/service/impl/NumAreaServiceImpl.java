package com.jsc.hotspot.accept.service.impl;

import com.jsc.hotspot.accept.service.NumAreaService;
import com.jsc.hotspot.db.dao.NumAreaMapper;
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
public class NumAreaServiceImpl implements NumAreaService {
    @Autowired
    private NumAreaMapper numAreaMapper;
    @Override
    public NumArea selectNumArea(String Mobile) {
        return numAreaMapper.selectNumArea(Mobile);
    }
}
