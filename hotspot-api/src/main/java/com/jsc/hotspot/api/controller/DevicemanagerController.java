package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.DeviceService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/device")
@RestController
public class DevicemanagerController {

    @Autowired
    private DeviceService deviceService;
    @GetMapping
    public Object query(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10")Integer limit,
                                      String devName, String devType, String devNum){
        List<HotFrontDevice> devices=deviceService.getDevices(page, limit,devName,devType,devNum);
        return ResponseUtil.okList(devices);
    }

    /**
     * 后台参数验证
     * @param hotFrontDevice
     * @return
     */
    private Object validate(HotFrontDevice hotFrontDevice){

        if (StringUtils.isEmpty(hotFrontDevice.getDevName())){
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(hotFrontDevice.getDevNum())){
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(String.valueOf(hotFrontDevice.getDevType()))){
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(hotFrontDevice.getIpAddr())){
            return ResponseUtil.badArgument();
        }
        return null;
    }
    @PostMapping
    public Object add(@RequestBody HotFrontDevice hotFrontDevice){
        Object error=validate(hotFrontDevice);
        if(error!=null){
            return  error;
        }
        deviceService.add(hotFrontDevice);
        return ResponseUtil.ok(hotFrontDevice);
    }

    @PutMapping
    public Object upadte(@RequestBody HotFrontDevice hotFrontDevice){
        Object error=validate(hotFrontDevice);
        if(error!=null){
            return  error;
        }
        deviceService.updateById(hotFrontDevice);
        return ResponseUtil.ok();
    }

    @DeleteMapping
    public Object delete(@RequestBody String ids){
        return ResponseUtil.deleteDataFailed();
    }

}
