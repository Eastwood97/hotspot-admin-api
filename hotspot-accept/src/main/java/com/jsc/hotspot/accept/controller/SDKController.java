package com.jsc.hotspot.accept.controller;

import com.jsc.hotspot.accept.adapter.AbstractDllAdapter;
import com.jsc.hotspot.accept.adapter.HaiKDllInterfaceAdapter;
import com.jsc.hotspot.accept.adapter.impl.HaiKDllAdapterImpl;
import com.jsc.hotspot.common.bean.FileInfo;
import com.jsc.hotspot.common.bean.VideoDownLoadBean;
import com.jsc.hotspot.common.biz.BizResult;
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
    private HaiKDllInterfaceAdapter haiKDllInterfaceAdapter;

    /**
     * 上传数据到摄像头通用接口
     * @param fileInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public BizResult<Boolean> uploadImgToCamera(@RequestBody FileInfo fileInfo){
        return haiKDllInterfaceAdapter.uploadToHaiKCamera(fileInfo);
    }

    /**
     * 下載視頻接口
     * @return
     */
    @RequestMapping(value = "download/video", method = RequestMethod.POST)
    public BizResult<Boolean> downloadVideo(VideoDownLoadBean videoDownLoadBean){
        return haiKDllInterfaceAdapter.downLoadVideo(videoDownLoadBean);
    }
}
