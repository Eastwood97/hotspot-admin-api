package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.HotTargetCar;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import com.jsc.hotspot.db.domain.Region;

import java.util.List;

/**
 * @author tzm
 * 处理区域管理业务接口
 */
public interface RegionService {
    /**
     * 分页查询
     * @param page
     * @param limit
     * @param regionName
     * @param state
     * @return
     */
    List<Region> query(Integer page, Integer limit, String regionName, Integer state);

    List<Region> selectAll();

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteById(String ids);

    /**
     * 添加
     * @param region
     */
    void add(Region region);

    /**
     * 编辑
     * @param region
     * @return
     */
    int updateById(Region region);


}
