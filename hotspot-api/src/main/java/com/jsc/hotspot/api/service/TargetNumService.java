package com.jsc.hotspot.api.service;


import com.jsc.hotspot.db.domain.HotTargetInfo;

import java.util.List;
/**
 * @author tzm
 * @desc 处理目标号码的相关业务
 */
public interface TargetNumService {
    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @param imsi
     * @param imei
     * @param isdn
     * @return
     */
    List<HotTargetInfo> query(Integer page, Integer limit,String targetName,String imsi,String imei,String isdn);

    /**
     * 编辑布控名单
     * @param targetInfo
     * @return
     */
    int updateById(HotTargetInfo targetInfo);

    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    boolean deleteById(String targetIds);

    /**
     * 添加目标
     * @param targetInfo
     */
    void add(HotTargetInfo targetInfo);

    /**
     * 获取所有的目标信息
     * @return
     */
    List<HotTargetInfo> getAllTargetNum();

    /**
     * 批量插入数据
     * @param targetList
     * @return
     */
  boolean insertForeach(List<HotTargetInfo> targetList);
}
