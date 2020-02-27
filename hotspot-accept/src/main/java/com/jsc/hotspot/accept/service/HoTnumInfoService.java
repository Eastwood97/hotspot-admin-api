package com.jsc.hotspot.accept.service;

import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.entity.PageResult;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 08:28
 * @Description:
 */
public interface HoTnumInfoService {
    //插入取号信息
    void insertHoTnumInfoNum(HotNumInfo hotNumInfoDAO);
    //15天统计
    List getHoTnumInfoDateNum();
}
