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
    PageResult findHotNumInfo(int page, int rows, HotNumInfo hotNumInfoDAO);
    void deleteHotNumInfo(String ids);
    Long getHoTnumInfoNum();
    List getHoTnumInfoDateNum();
    Long getTodayHoTnumInfoNum();
    void insertHoTnumInfoNum(HotNumInfo hotNumInfoDAO);
}
