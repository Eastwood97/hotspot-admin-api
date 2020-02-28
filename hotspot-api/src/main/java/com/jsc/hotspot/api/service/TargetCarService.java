package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.HotTargetCar;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * @author tzm
 * @desc 处理目标车牌的相关业务
 */
public interface TargetCarService {

    /**
     * 查询布控的车牌
     * @param plateNumber 车牌号
     * @param page
     * @param limit
     * @return
     */
    List<HotTargetCar> query(String plateNumber,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit);

    /**
     * 添加目标车牌
     * @param hotTargetCar
     */
    void add(HotTargetCar hotTargetCar);

    /**
     * 根据车牌查出一条数据
     * @param plateNumber
     * @return
     */
    HotTargetCar findByNumber(String plateNumber);

    /**
     * 修改布控车牌信息
     * @param hotTargetCar
     * @return
     */
    int update(HotTargetCar hotTargetCar);

    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    boolean deleteById(String targetIds);

}
