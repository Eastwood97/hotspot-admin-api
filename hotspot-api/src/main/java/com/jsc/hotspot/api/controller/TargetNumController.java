package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.TargetNumService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tzm
 * @desc 布控号码的数据接口
 *
 */
@RestController
@RequestMapping("/admin/targetNum")
public class TargetNumController {
    private final Log logger= LogFactory.getLog(TargetNumController.class);

    @Autowired
    public TargetNumService targetNumService;

    @GetMapping
    public Object getTargetNum(String targetName,String imsi,String imei,String isdn,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit
                          ){
        List<HotTargetInfo> targetNumList=targetNumService.query(page,limit,targetName,imsi,imei,isdn);
        return ResponseUtil.okList(targetNumList);
    }

    /**
     * 参数验证
     * @param targetInfo
     * @return
     */
    private Object validate(HotTargetInfo targetInfo){
        String targetName=targetInfo.getTargetName();
        if(StringUtils.isEmpty(targetName)){
            return ResponseUtil.badArgument();
        }

        String imsi=targetInfo.getImsi();
        String imei=targetInfo.getImei();
        String isdn=targetInfo.getIsdn();

        Boolean flagImsi=StringUtils.isEmpty(imsi);
        Boolean flagImei=StringUtils.isEmpty(imei);
        Boolean flagIsdn=StringUtils.isEmpty(isdn);

        //imsi imei  isdn 不能都为空
        if (flagImei&&flagImsi&&flagIsdn){
            return  ResponseUtil.badArgument();
        }

        return null;

    }
    @PostMapping
    public Object add(@RequestBody HotTargetInfo targetNum){
        Object error=validate(targetNum);
        if(error!=null){
            return  error;
        }
        targetNumService.add(targetNum);
        return ResponseUtil.ok(targetNum);
    }

    @PutMapping
    public Object Update(@RequestBody HotTargetInfo targetInfo){
        Object error=validate(targetInfo);
        if(error!=null){
            return  error;
        }
        if(targetNumService.updateById(targetInfo)==0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(targetInfo);
    }

    @DeleteMapping
    public Object deleteById(@RequestBody String targetIds){
        if(targetNumService.deleteById(targetIds)){
            return  ResponseUtil.ok();
        }else {
            return  ResponseUtil.deleteDataFailed();
        }
    }
}
