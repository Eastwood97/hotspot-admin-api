package com.jsc.hotspot.accept.adapter;

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
    void UploadSend(InputStream inputStream);
    void UploadFaceLinData(NativeLong lUserID, InputStream inputStream);
    NativeLong getUploadState();
    void SetFaceAppendData(NativeLong lUserID);
    void DeleteFaceAppendData(NativeLong lUserID);
    void GetFaceAppendData(NativeLong lUserID);
    void FDLibGetButtonClick(NativeLong lUserID);

}
