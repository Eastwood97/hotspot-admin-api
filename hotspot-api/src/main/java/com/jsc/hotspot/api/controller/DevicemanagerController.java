package com.jsc.hotspot.api.controller;

import com.alibaba.fastjson.JSON;
import com.jsc.hotspot.api.service.DeviceService;
import com.jsc.hotspot.api.utils.HttpUtil;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tzm
 * @desc 设备管理
 */
@RequestMapping("admin/device")
@RestController
public class DevicemanagerController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 分页查询数据
     * @param page
     * @param limit
     * @param devName
     * @param devType
     * @param devNum
     * @return
     */
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

    /**
     * 添加设备
     * @param hotFrontDevice
     * @return
     */
    @PostMapping
    public Object add(@RequestBody HotFrontDevice hotFrontDevice){
        Object error=validate(hotFrontDevice);
        if(error!=null){
            return  ResponseUtil.badArgumentValue();
        }
        if (null!=hotFrontDevice.getIsRegister()&&1==hotFrontDevice.getIsRegister()){
            String respone= HttpUtil.sendGet("","http://127.0.0.1:9090/sdk/register");
            BizResult<String> data=JSON.parseObject(respone, BizResult.class);
            if(!data.getFlag()){
                hotFrontDevice.setIsRegister((byte) 0);
                deviceService.add(hotFrontDevice);
                return  ResponseUtil.fail(-1,data.getData());
            }
        }
        deviceService.add(hotFrontDevice);
        return ResponseUtil.ok(hotFrontDevice);
    }

    /**
     * 编辑设备信息
     * @param hotFrontDevice
     * @return
     */
    @PutMapping
    public Object upadte(@RequestBody HotFrontDevice hotFrontDevice){
        Object error=validate(hotFrontDevice);
        if(error!=null){
            return  error;
        }
        int flag=deviceService.selectById(hotFrontDevice).getIsRegister();
        if (null!=hotFrontDevice.getIsRegister()&&hotFrontDevice.getIsRegister()==1&&flag==0){
            String respone= HttpUtil.sendGet("","http://127.0.0.1:9090/sdk/register");
            BizResult<String> data=JSON.parseObject(respone, BizResult.class);
            if(!data.getFlag()){
                hotFrontDevice.setIsRegister((byte) 0);
                deviceService.updateById(hotFrontDevice);
                return  ResponseUtil.fail(-1,data.getData());
            }
        }
        if(hotFrontDevice.getIsRegister()==0&&flag==1){
            //取消注册
            String respone= HttpUtil.sendGet("","http://127.0.0.1:9090/sdk/unRegister");
            BizResult<String> data=JSON.parseObject(respone, BizResult.class);
            if(!data.getFlag()){
                hotFrontDevice.setIsRegister((byte) 1);
                deviceService.updateById(hotFrontDevice);
                return  ResponseUtil.fail(-1,data.getData());
            }
        }
        deviceService.updateById(hotFrontDevice);
        return ResponseUtil.ok();
    }

    /**
     * 批量删除设备
     * @param ids
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody String ids) {
        if (deviceService.deleteById(ids)) {
            return  ResponseUtil.ok();
        } else {
            return ResponseUtil.deleteDataFailed();
        }
    }
    /**
     * 获取设备
     * @param regionId
     * @return
     */
    @RequestMapping("/getDevId")
    public Object getDevId(Integer regionId){
        List<HotFrontDevice> devices=deviceService.getregionIdList(regionId);
        return ResponseUtil.okList(devices);
    }
}
