package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.CaptureCarService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.CameraCatInfo;
import com.jsc.hotspot.db.domain.CaptureCar;
import com.jsc.hotspot.db.domain.HotTargetCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tzm
 * @desc 抓拍车牌的数据接口
 */
@RestController
@RequestMapping("/admin/captureCar")
public class CaptureCarController {

    @Autowired
    private CaptureCarService captureCarService;

    /**
     * 分页查询抓拍车牌
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @param plateNumber
     * @return
     */
    @GetMapping
    public Object query(Integer page, Integer limit,String startTime,String endTime,String plateNumber){
        List<CaptureCar> cameraCatInfos=captureCarService.query(page,limit,startTime,endTime,plateNumber);
        return ResponseUtil.okList(cameraCatInfos);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody String ids) {
        if (captureCarService.deleteById(ids)) {
            return  ResponseUtil.ok();
        } else {
            return ResponseUtil.deleteDataFailed();
        }
    }

}
