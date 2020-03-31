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

    BizResult<String> downLoadVideo(VideoDownLoadBean videoDownLoadBean);

    void initdll();

    void exit();

    BizResult<String> register();

    void Logout();

    BizResult<String> SetupAlarmChan();

    void GetShortVideoFile(VideoDownLoadBean videoDownLoadBean);

    void getLibrary();

    BizResult<Boolean> search();

    void unregister();

    BizResult<Boolean> deletePic(String pid);
}
