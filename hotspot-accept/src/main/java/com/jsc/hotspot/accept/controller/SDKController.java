package com.jsc.hotspot.accept.controller;

import com.jsc.hotspot.accept.adapter.AbstractDllAdapter;
import com.jsc.hotspot.accept.adapter.HaiKDllInterfaceAdapter;
import com.jsc.hotspot.accept.adapter.impl.HaiKDllAdapterImpl;
import com.jsc.hotspot.accept.service.CameraService;
import com.jsc.hotspot.common.bean.FileInfo;
import com.jsc.hotspot.common.bean.VideoDownLoadBean;
import com.jsc.hotspot.common.biz.BizResult;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author huixing
 * @description 调用通用的SDK
 * @date 2019/12/26
 */
@Controller
@RequestMapping("sdk")
public class SDKController {


    @Autowired
    private CameraService cameraService;

    /**
     * 上传数据到摄像头通用接口
     * @param fileInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public BizResult<Boolean> uploadImgToCamera(@RequestBody FileInfo fileInfo){
        return cameraService.uploadToHaiKCamera(fileInfo);
    }

    /**
     * 下載視頻接口
     * @return
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    @ResponseBody
    public BizResult<String> downloadVideo(@RequestBody VideoDownLoadBean videoDownLoadBean){
        return cameraService.downLoadVideo(videoDownLoadBean);
    }

    /**
     * 进行注册操作
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public BizResult<String> register(){
        return cameraService.register();
    }

    /**
     * 取消注册
     * @return
     */
    @RequestMapping(value = "unregister", method = RequestMethod.GET)
    @ResponseBody
    public BizResult<Boolean> unregister(){
        return cameraService.unregister();
    }
}
