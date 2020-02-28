package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.PlateNumberCompareService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.CaptureCar;
import com.jsc.hotspot.db.domain.PlateNumberCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tzm
 * @desc 车牌比对与关联的数据接口
 */
@RestController
@RequestMapping("/admin/plateNumberCompare")
public class PlateNumberCompareController {

    @Autowired
    private PlateNumberCompareService plateNumberCompareService;

    /**
     * 分页查询车牌比对与关联信息
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @param plateNumber
     * @return
     */
    @GetMapping
    public Object query(Integer page, Integer limit,String startTime,String endTime,String plateNumber){
        List<PlateNumberCompare> plateNumberCompares=plateNumberCompareService.query(page,limit,startTime,endTime,plateNumber);
        return ResponseUtil.okList(plateNumberCompares);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody String ids) {
        if (plateNumberCompareService.deleteById(ids)) {
            return  ResponseUtil.ok();
        } else {
            return ResponseUtil.deleteDataFailed();
        }
    }
}
