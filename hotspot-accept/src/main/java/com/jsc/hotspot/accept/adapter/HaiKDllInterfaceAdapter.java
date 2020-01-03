package com.jsc.hotspot.accept.adapter;

import com.jsc.hotspot.common.bean.FileInfo;
import com.jsc.hotspot.common.bean.VideoDownLoadBean;
import com.jsc.hotspot.common.biz.BizResult;

/**
 * @author huixing
 * @description
 * @date 2019/12/26
 */
public interface HaiKDllInterfaceAdapter extends AbstractDllAdapter{
    BizResult<Boolean> uploadToHaiKCamera(FileInfo fileInfo);

    void getCameraAbilities();

    void GetUploadState();

    void UploadClose();

    void StartDownload();

    int getlUserID();

    BizResult<Boolean> downLoadVideo(VideoDownLoadBean videoDownLoadBean);

}
