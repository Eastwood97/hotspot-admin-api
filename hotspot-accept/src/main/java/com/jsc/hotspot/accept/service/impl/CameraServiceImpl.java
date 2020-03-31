package com.jsc.hotspot.accept.service.impl;

import com.jsc.hotspot.accept.adapter.HaiKDllInterfaceAdapter;
import com.jsc.hotspot.accept.service.CameraService;
import com.jsc.hotspot.common.bean.FileInfo;
import com.jsc.hotspot.common.bean.VideoDownLoadBean;
import com.jsc.hotspot.common.biz.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huixing
 * @description 摄像头服务
 * @date 2020/1/13
 */
@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private HaiKDllInterfaceAdapter haiKDllInterfaceAdapter;
    @Override
    public BizResult<Boolean> uploadToHaiKCamera(FileInfo fileInfo) {
        return haiKDllInterfaceAdapter.uploadToHaiKCamera(fileInfo);
    }

    @Override
    public BizResult<String> downLoadVideo(VideoDownLoadBean videoDownLoadBean) {
        return haiKDllInterfaceAdapter.downLoadVideo(videoDownLoadBean);
    }

    @Override
    public BizResult<String> register() {
        BizResult<String> registerResult = haiKDllInterfaceAdapter.register();
        if (registerResult.getFlag()) {
            BizResult<String> alermChan = haiKDllInterfaceAdapter.SetupAlarmChan();
            if (alermChan.getFlag()){
                haiKDllInterfaceAdapter.search();
                return BizResult.create("注册成功");
            }else {
                return alermChan;
            }
        }else {
            return registerResult;
        }
    }

    @Override
    public BizResult<Boolean> unregister() {
        haiKDllInterfaceAdapter.unregister();
        return BizResult.create(true);
    }

    @Override
    public BizResult<Boolean> deletePic(String pid) {
        return haiKDllInterfaceAdapter.deletePic(pid);
    }
}
