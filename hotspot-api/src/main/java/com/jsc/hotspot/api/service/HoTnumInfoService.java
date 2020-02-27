package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 08:28
 * @Description:
 */
public interface HoTnumInfoService {
    //分页查询展示
    PageResult findHotNumInfo(int groupId, int page, int rows, HotNumInfo hotNumInfoDAO);
    //删除取号信息
    void deleteHotNumInfo(String ids);
    //获取数量
    Long getHoTnumInfoNum();
    //15天统计
    List getHoTnumInfoDateNum();
    //今日数量
    Long getTodayHoTnumInfoNum();
    //人流分析
    List<Map> getTraffic(Integer devId, String[] createTime);
    //获取不同国家
    Object getDifferentCountries(Integer devId, String[] createTime);

    PageResult selecttongxingList(Integer currentPage, Integer pageSize, Integer createTime,String imsi);

    /**
     * 按天获取取号数据
     * @return
     */
    Map<String, Integer> getNumInfoByDay();

    /**
     * 按月获取取号数据
     * @return
     */
    Map<String, Integer> getNumInfoByMonth();


    /**
     * 当天捕获到的号码总数
     * @return
     */
    Long currentImsiNums();

    /**
     * 手机号码捕获总数
     * @return
     */
    Long countImsiCatNums();
}
