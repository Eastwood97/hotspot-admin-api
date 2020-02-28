package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.TargetCarService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotTargetCar;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TZM
 * @desc 布控车牌的数据接口
 */
@RestController
@RequestMapping("/admin/targetCar")
public class TargetCarController {

    @Autowired
    private TargetCarService targetCarService;

    /**
     * 查询布控的车牌
     * @param plateNumber
     * @param page
     * @param limit
     * @return
     */
    @GetMapping
    public  Object getTargetCar(String plateNumber,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer limit){

        List<HotTargetCar> targetCarList=targetCarService.query(plateNumber,page,limit);
        return ResponseUtil.okList(targetCarList);

    }

    /**
     * 添加目标车牌
     * @param hotTargetCar
     * @return
     */
    @PostMapping
    public Object add(@RequestBody HotTargetCar hotTargetCar){
        String plateNumber=hotTargetCar.getPlateNumber();
        if(null==plateNumber||StringUtils.isEmpty(plateNumber)) {
                return ResponseUtil.badArgument();
        }
        targetCarService.add(hotTargetCar);
        return ResponseUtil.ok(hotTargetCar);
    }

    /**
     * 修改目标车牌
     * @param hotTargetCar
     * @return
     */
    @PutMapping
    public  Object update(@RequestBody HotTargetCar hotTargetCar){
        String plateNumber=hotTargetCar.getPlateNumber();
        if(null==plateNumber||StringUtils.isEmpty(plateNumber)) {
            return ResponseUtil.badArgument();
        }
        HotTargetCar targetCar=targetCarService.findByNumber(hotTargetCar.getPlateNumber());
        if (targetCar.getTargetId()!=hotTargetCar.getTargetId()){
            return  ResponseUtil.fail(-1,"车牌号已存在");
        }
        if(targetCarService.update(hotTargetCar)==0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(hotTargetCar);
    }

    /**
     *批量删除
     * @param targetIds
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody String targetIds) {
        if (targetCarService.deleteById(targetIds)) {
            return  ResponseUtil.ok();
        } else {
            return ResponseUtil.deleteDataFailed();
        }
    }
}
