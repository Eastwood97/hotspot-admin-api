package com.jsc.hotspot.accept.service;

import com.jsc.hotspot.common.bean.FileInfo;
import com.jsc.hotspot.common.bean.VideoDownLoadBean;
import com.jsc.hotspot.common.biz.BizResult;

/**
 * @author huixing
 * @description 摄像头服务接口
 * @date 2020/1/13
 */
public interface CameraService {
    /**
     * 上传布控照片
     * @param fileInfo
     * @return
     */
    BizResult<Boolean> uploadToHaiKCamera(FileInfo fileInfo);

    /**
     * 下载小视频
     * @param fileInfo
     * @return
     */
    BizResult<String> downLoadVideo(VideoDownLoadBean fileInfo);

    /**
     * 注册
     * @return
     */
    BizResult<String> register();

    /**
     * 不注册
     * @return
     */
    BizResult<Boolean> unregister();

    /**
     * 删除照片数据
     * @return
     */
    BizResult<Boolean> deletePic(String pid);
}
