package com.jsc.hotspot.accept.adapter;

import com.jsc.hotspot.common.bean.FileInfo;
import com.sun.jna.NativeLong;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author huixing
 * @description 人脸相关接口
 * @date 2019/12/31
 */
public interface FaceInterface {
    boolean SearchFDLib(NativeLong lUserID);
    boolean CreateFDLib(String FDLibName, NativeLong lUserID) throws DocumentException;
    boolean DeleteFDLib(NativeLong lUserID);
    boolean UploadFile(int index, NativeLong lUserID);
    void UploadSend(InputStream inputStream, FileInfo fileInfo);
    void UploadFaceLinData(NativeLong lUserID, InputStream inputStream, FileInfo fileInfo);
    NativeLong getUploadState();
    void SetFaceAppendData(NativeLong lUserID);
    void DeleteFaceAppendData(NativeLong lUserID);
    void GetFaceAppendData(NativeLong lUserID);
    void FDLibGetButtonClick(NativeLong lUserID);

}
