package com.jsc.hotspot.accept.adapter.impl;

import com.jsc.hotspot.accept.adapter.FaceInterface;
import com.jsc.hotspot.accept.adapter.HaiKDllInterfaceAdapter;
import com.jsc.hotspot.accept.sdk.HCNetSDK;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2019/12/31
 */
public class HaiKDllFaceImpl implements FaceInterface {

    private final Log logger = LogFactory.getLog(HaiKDllAdapterImpl.class);


    @Autowired
    public static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    public int m_FDID; // 人脸库ID
    public boolean m_isSupportFDLib; // 是否支持人脸功能
    public List<HCNetSDK.NET_DVR_FDLIB_PARAM> m_FDLibList;
    public NativeLong m_lUploadHandle;
    public NativeLong m_UploadStatus;
    public String m_picID;

    public HaiKDllFaceImpl(){
        m_FDLibList = new ArrayList<HCNetSDK.NET_DVR_FDLIB_PARAM>();
        m_lUploadHandle = new NativeLong(-1);
        m_UploadStatus = new NativeLong(-1);

            //查询是否支持人脸库
        m_isSupportFDLib = true;
//            if (GetFaceCapabilities()) {
//                m_isSupportFDLib = true;
//                searchFDLibButtonClick();
//            } else {
//                m_isSupportFDLib = false;
//            }
    }

    public boolean SearchFDLib(NativeLong lUserID)
    {
        try {
            if (m_isSupportFDLib)
            {
                //返回true，说明支持人脸
                HCNetSDK.NET_DVR_XML_CONFIG_INPUT	struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
                struInput.dwSize = struInput.size();

                String str = "GET /ISAPI/Intelligent/FDLib\r\n";
                HCNetSDK.BYTE_ARRAY ptrUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
                System.arraycopy(str.getBytes(), 0, ptrUrl.byValue, 0, str.length());
                ptrUrl.write();
                struInput.lpRequestUrl = ptrUrl.getPointer();
                struInput.dwRequestUrlLen = str.length();

                HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
                struOutput.dwSize = struOutput.size();

                HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_DATA_LEN);
                struOutput.lpOutBuffer = ptrOutByte.getPointer();
                struOutput.dwOutBufferSize = HCNetSDK.ISAPI_DATA_LEN;

                HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_STATUS_LEN);
                struOutput.lpStatusBuffer = ptrStatusByte.getPointer();
                struOutput.dwStatusSize = HCNetSDK.ISAPI_STATUS_LEN;
                struOutput.write();

                if (hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput))
                {
                    String xmlStr = struOutput.lpOutBuffer.getString(0);

                    //dom4j解析xml
                    Document document = DocumentHelper.parseText(xmlStr);
                    //获取根节点元素对象
                    Element FDLibBaseCfgList = document.getRootElement();

                    //同时迭代当前节点下面的所有子节点
                    Iterator<Element> iterator = FDLibBaseCfgList.elementIterator();
                    while(iterator.hasNext())
                    {
                        HCNetSDK.NET_DVR_FDLIB_PARAM tmp = new HCNetSDK.NET_DVR_FDLIB_PARAM();
                        Element e = iterator.next();
                        Iterator<Element> iterator2 = e.elementIterator();
                        while(iterator2.hasNext())
                        {
                            Element e2 = iterator2.next();
                            if (e2.getName().equals("id"))
                            {
                                String id = e2.getText();
                                tmp.dwID = Integer.parseInt(id);
                            }
                            if (e2.getName().equals("name"))
                            {
                                tmp.szFDName = e2.getText();
                            }
                            if (e2.getName().equals("FDID"))
                            {
                                String fdid = e2.getText();
                                tmp.dwFDID = Integer.parseInt(fdid);
                            }
                        }
                        m_FDLibList.add(tmp);
                    }

                }
                else
                {
                    int code = hCNetSDK.NET_DVR_GetLastError();
                    JOptionPane.showMessageDialog(null, "创建人脸库失败: " + code);
                    return false;
                }
            }
            else
            {
                return false;
            }
        } catch (DocumentException ex) {
            return false;
        }
        return true;
    }


    //创建人脸库
    public boolean CreateFDLib(String FDLibName, NativeLong lUserID) throws DocumentException {
        try {
            if (m_isSupportFDLib)
            {
                //返回true，说明支持人脸
                HCNetSDK.NET_DVR_XML_CONFIG_INPUT	struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
                struInput.dwSize = struInput.size();

                String str = "POST /ISAPI/Intelligent/FDLib\r\n";
                HCNetSDK.BYTE_ARRAY ptrUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
                System.arraycopy(str.getBytes(), 0, ptrUrl.byValue, 0, str.length());
                ptrUrl.write();
                struInput.lpRequestUrl = ptrUrl.getPointer();
                struInput.dwRequestUrlLen = str.length();

                String strInBuffer = new String("<CreateFDLibList><CreateFDLib><id>1</id><name>" + FDLibName + "</name><thresholdValue>1</thresholdValue><customInfo /></CreateFDLib></CreateFDLibList>");
                HCNetSDK.BYTE_ARRAY ptrByte = new HCNetSDK.BYTE_ARRAY(10*HCNetSDK.BYTE_ARRAY_LEN);
                ptrByte.byValue = strInBuffer.getBytes();
                ptrByte.write();
                struInput.lpInBuffer = ptrByte.getPointer();
                struInput.dwInBufferSize = strInBuffer.length();
                struInput.write();

                HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
                struOutput.dwSize = struOutput.size();

                HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_DATA_LEN);
                struOutput.lpOutBuffer = ptrOutByte.getPointer();
                struOutput.dwOutBufferSize = HCNetSDK.ISAPI_DATA_LEN;

                HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_STATUS_LEN);
                struOutput.lpStatusBuffer = ptrStatusByte.getPointer();
                struOutput.dwStatusSize = HCNetSDK.ISAPI_STATUS_LEN;
                struOutput.write();

                if (hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput))
                {
                    String xmlStr = struOutput.lpOutBuffer.getString(0);

                    //dom4j解析xml
                    Document document = DocumentHelper.parseText(xmlStr);
                    //获取根节点元素对象
                    Element FDLibInfoList = document.getRootElement();

                    //同时迭代当前节点下面的所有子节点
                    Iterator<Element> iterator = FDLibInfoList.elementIterator();
                    Element FDLibInfo = iterator.next();
                    Iterator<Element> iterator2 = FDLibInfo.elementIterator();
                    while(iterator2.hasNext())
                    {
                        Element e = iterator2.next();
                        if (e.getName().equals("FDID"))
                        {
                            String id = e.getText();
                            m_FDID = Integer.parseInt(id);
                        }
                        System.out.println( e.getName() + "：" + e.getText());
                    }
                }
                else
                {
                    int code = hCNetSDK.NET_DVR_GetLastError();
                    if (logger.isDebugEnabled())
                        logger.debug( "创建人脸库失败: " + code);
                    return false;
                }
            }
            else
            {
                return false;
            }
        } catch (DocumentException ex) {
            logger.error( "创建人脸库失败: ");
            return false;
        }
        return true;
    }

    public boolean DeleteFDLib(NativeLong lUserID)
    {
        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struInput.dwSize = struInput.size();

        int index = 0;
        int id = m_FDLibList.get(index).dwFDID;
        String str = "DELETE /ISAPI/Intelligent/FDLib/" + id;
        HCNetSDK.BYTE_ARRAY ptrUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
        System.arraycopy(str.getBytes(), 0, ptrUrl.byValue, 0, str.length());
        ptrUrl.write();
        struInput.lpRequestUrl = ptrUrl.getPointer();
        struInput.dwRequestUrlLen = str.length();


        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struOutput.dwSize = struOutput.size();
        struOutput.write();

        if (hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput))
        {
            logger.debug( "删除人脸库成功");
            return true;
        }
        else
        {
            int code = hCNetSDK.NET_DVR_GetLastError();
            logger.debug("删除人脸库失败: " + code);
            return false;
        }
    }

    public boolean UploadFile(int index, NativeLong lUserID)
    {
        if (m_isSupportFDLib)
        {
            //返回true，说明支持人脸
            HCNetSDK.NET_DVR_FACELIB_COND	struInput = new HCNetSDK.NET_DVR_FACELIB_COND();
            struInput.dwSize = struInput.size();
            struInput.szFDID = String.valueOf(m_FDLibList.get(index).dwFDID).getBytes();
            struInput.byConcurrent = 0;
            struInput.byCover = 1;
            struInput.byCustomFaceLibID = 0;

            struInput.write();
            Pointer lpInput = struInput.getPointer();

            NativeLong ret = hCNetSDK.NET_DVR_UploadFile_V40(lUserID, HCNetSDK.IMPORT_DATA_TO_FACELIB, lpInput, struInput.size(), null, null, 0);
            if (ret.longValue() == -1)
            {
                int code = hCNetSDK.NET_DVR_GetLastError();
                logger.debug("上传图片文件失败: " + code);
                return false;
            }
            else
            {
                m_lUploadHandle = ret;
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public void UploadSend(InputStream picfile)
    {
        FileInputStream xmlfile = null;
        int picdataLength = 0;
        int xmldataLength = 0;

        try{
            xmlfile = new FileInputStream(new File(System.getProperty("user.dir") + "\\data.xml"));
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }


        try{
            picdataLength = picfile.available();
            xmldataLength = xmlfile.available();
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }


        if(picdataLength < 0 || xmldataLength < 0)
        {
            System.out.println("input file/xml dataSize < 0");
            return;
        }

        HCNetSDK.BYTE_ARRAY ptrpicByte = new HCNetSDK.BYTE_ARRAY(picdataLength);
        HCNetSDK.BYTE_ARRAY ptrxmlByte = new HCNetSDK.BYTE_ARRAY(xmldataLength);



        try {
            picfile.read(ptrpicByte.byValue);
            xmlfile.read(ptrxmlByte.byValue);
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        ptrpicByte.write();
        ptrxmlByte.write();


        HCNetSDK.NET_DVR_SEND_PARAM_IN struSendParam = new HCNetSDK.NET_DVR_SEND_PARAM_IN();

        struSendParam.pSendData = ptrpicByte.getPointer();
        struSendParam.dwSendDataLen = picdataLength;
        struSendParam.pSendAppendData = ptrxmlByte.getPointer();
        struSendParam.dwSendAppendDataLen = xmldataLength;
        if(struSendParam.pSendData == null || struSendParam.pSendAppendData == null || struSendParam.dwSendDataLen == 0 || struSendParam.dwSendAppendDataLen == 0)
        {
            System.out.println("input file/xml data err");
            return;
        }

        struSendParam.byPicType = 1;
        struSendParam.dwPicMangeNo = 0;
        struSendParam.write();

        NativeLong iRet = hCNetSDK.NET_DVR_UploadSend(m_lUploadHandle, struSendParam.getPointer(), null);

        System.out.println("iRet="+iRet);
        if(iRet.longValue() < 0)
        {
            System.out.println("NET_DVR_UploadSend fail,error=" + hCNetSDK.NET_DVR_GetLastError());
        }
        else
        {
            System.out.println("NET_DVR_UploadSend success");
            System.out.println("dwSendDataLen ="+struSendParam.dwSendDataLen);
            System.out.println("dwSendAppendDataLen ="+struSendParam.dwSendAppendDataLen);
        }

        try {
            picfile.close();
            xmlfile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void UploadFaceLinData(NativeLong lUserID, InputStream picStream)
    {
        if(m_lUploadHandle.longValue() != -1)
        {
            if(hCNetSDK.NET_DVR_UploadClose(m_lUploadHandle))
            {
                System.out.println("NET_DVR_UploadClose success");
            }
            else
            {
                System.out.println("NET_DVR_UploadClose fail,error=" + hCNetSDK.NET_DVR_GetLastError());
            }
        }

        int index = 0;
        UploadFile(index, lUserID);

        if (m_lUploadHandle.longValue() < 0)
        {
            System.out.println("NET_DVR_UploadFile_V40 fail,error=" + hCNetSDK.NET_DVR_GetLastError());
        }
        else
        {
            System.out.println("NET_DVR_UploadFile_V40 success");
        }

        Thread thread = new Thread()
        {
            public void run()
            {
                UploadSend(picStream);
                while(true)
                {
                    if (-1 == m_lUploadHandle.longValue())
                    {
                        return;
                    }

                    m_UploadStatus = getUploadState();
                    if(m_UploadStatus.longValue() == 1)
                    {
                        HCNetSDK.NET_DVR_UPLOAD_FILE_RET  struPicRet = new HCNetSDK.NET_DVR_UPLOAD_FILE_RET();
                        struPicRet.write();
                        Pointer lpPic= struPicRet.getPointer();

                        boolean bRet = hCNetSDK.NET_DVR_GetUploadResult(m_lUploadHandle, lpPic, struPicRet.size());
                        if(!bRet)
                        {
                            System.out.println("NET_DVR_GetUploadResult failed with:" + hCNetSDK.NET_DVR_GetLastError());
                        }
                        else
                        {
                            System.out.println("NET_DVR_GetUploadResult succ");
                            struPicRet.read();
                            m_picID = new String(struPicRet.sUrl);
                            System.out.println("PicID:" + m_picID);
                            logger.debug("图片上传成功 PID:" + m_picID);
                        }


                        if(hCNetSDK.NET_DVR_UploadClose(m_lUploadHandle))
                        {
                            m_lUploadHandle.setValue(-1);
                        }

                    }
                    else if(m_UploadStatus.longValue() >= 3 || m_UploadStatus.longValue() == -1)
                    {
                        System.out.println("m_UploadStatus = " + m_UploadStatus);
                        hCNetSDK.NET_DVR_UploadClose(m_lUploadHandle);
                        m_lUploadHandle.setValue(-1);
                        break;
                    }
                }
            }
        };
        thread.start();
    }

    public NativeLong getUploadState()
    {
        IntByReference pInt = new IntByReference(0);
        m_UploadStatus = hCNetSDK.NET_DVR_GetUploadState(m_lUploadHandle, pInt);
        if(m_UploadStatus.longValue() == -1)
        {
            System.out.println("NET_DVR_GetUploadState fail,error=" + hCNetSDK.NET_DVR_GetLastError());
        }
        else if(m_UploadStatus.longValue() == 2)
        {
            System.out.println("is uploading!!!!  progress = " + pInt.getValue());
        }
        else if(m_UploadStatus.longValue()  == 1)
        {
            System.out.println("progress = " + pInt.getValue());
            System.out.println("Uploading Succ!!!!!");
        }
        else
        {
            System.out.println("NET_DVR_GetUploadState fail  m_UploadStatus=" + m_UploadStatus);
            System.out.println("NET_DVR_GetUploadState fail,error=" + hCNetSDK.NET_DVR_GetLastError());
        }
        return m_UploadStatus;
    }

    public void SetFaceAppendData(NativeLong lUserID)
    {
        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struInput.dwSize = struInput.size();

        // TODO 后期会添加不同的库
        int index = 0;
        int id = m_FDLibList.get(index).dwFDID;

        String str = "PUT /ISAPI/Intelligent/FDLib/" + id + "/picture/" + m_picID;
        HCNetSDK.BYTE_ARRAY ptrSetFaceAppendDataUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
        System.arraycopy(str.getBytes(), 0, ptrSetFaceAppendDataUrl.byValue, 0, str.length());
        ptrSetFaceAppendDataUrl.write();
        struInput.lpRequestUrl = ptrSetFaceAppendDataUrl.getPointer();
        struInput.dwRequestUrlLen = str.length();


        String strInBuffer = "<FaceAppendData><name>Name</name><bornTime>2000-01-01</bornTime><sex>male</sex><province /><certificateType /><certificateNumber /></FaceAppendData>";

        HCNetSDK.BYTE_ARRAY ptrByte = new HCNetSDK.BYTE_ARRAY(10*1024);
        ptrByte.byValue = strInBuffer.getBytes();
        ptrByte.write();
        struInput.lpInBuffer = ptrByte.getPointer();
        struInput.dwInBufferSize = strInBuffer.length();
        struInput.write();

        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struOutput.dwSize = struOutput.size();

        HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_DATA_LEN);
        struOutput.lpOutBuffer = ptrOutByte.getPointer();
        struOutput.dwOutBufferSize = HCNetSDK.ISAPI_DATA_LEN;

        HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_STATUS_LEN);
        struOutput.lpStatusBuffer = ptrStatusByte.getPointer();
        struOutput.dwStatusSize = HCNetSDK.ISAPI_STATUS_LEN;
        struOutput.write();

        if(!hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput))
        {
            System.out.println("PUT /ISAPI/Intelligent/FDLib/1/picture/1 failed with:" + lUserID + " "+ hCNetSDK.NET_DVR_GetLastError());
            return;
        }
        else
        {
            System.out.println("PUT /ISAPI/Intelligent/FDLib/1/picture/1 success");
            System.out.println("dwReturnXMLSize="+struOutput.dwReturnedXMLSize);
        }
    }


    public void DeleteFaceAppendData(NativeLong lUserID)
    {
        int index = 0;
        int id = m_FDLibList.get(index).dwFDID;

        String str = "DELETE /ISAPI/Intelligent/FDLib/" + id + "/picture/" + m_picID;

        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struInput.dwSize = struInput.size();

        HCNetSDK.BYTE_ARRAY ptrDeleteFaceLibUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
        System.arraycopy(str.getBytes(), 0, ptrDeleteFaceLibUrl.byValue, 0, str.length());
        ptrDeleteFaceLibUrl.write();
        struInput.lpRequestUrl = ptrDeleteFaceLibUrl.getPointer();
        struInput.dwRequestUrlLen = str.length();
        struInput.lpInBuffer = null;
        struInput.dwInBufferSize = 0;
        struInput.write();

        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struOutput.dwSize = struOutput.size();
        struOutput.lpOutBuffer = null;
        struOutput.dwOutBufferSize = 0;

        HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_STATUS_LEN);
        struOutput.lpStatusBuffer = ptrStatusByte.getPointer();
        struOutput.dwStatusSize = HCNetSDK.ISAPI_STATUS_LEN;
        struOutput.write();

        if(!hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput))
        {
            System.out.println("lpRequestUrl:" + str);
            System.out.println("NET_DVR_STDXMLConfig DELETE failed with:" + " "+ hCNetSDK.NET_DVR_GetLastError());
        }
        else
        {
            System.out.println("lpRequestUrl:" + str);
            System.out.println("NET_DVR_STDXMLConfig DELETE Succ!!!!!!!!!!!!!!!");
            logger.debug("图片删除成功 PID:" + m_picID);
        }
    }

    public void GetFaceAppendData(NativeLong lUserID)
    {
        HCNetSDK.NET_DVR_XML_CONFIG_INPUT	struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struInput.dwSize = struInput.size();

        String str = "GET /ISAPI/Intelligent/FDLib/1/picture/1";
        HCNetSDK.BYTE_ARRAY ptrGetFaceAppendDataUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
        System.arraycopy(str.getBytes(), 0, ptrGetFaceAppendDataUrl.byValue, 0, str.length());
        ptrGetFaceAppendDataUrl.write();
        struInput.lpRequestUrl = ptrGetFaceAppendDataUrl.getPointer();
        struInput.dwRequestUrlLen = str.length();


        struInput.lpInBuffer = null;
        struInput.dwInBufferSize = 0;
        struInput.write();

        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struOutput.dwSize = struOutput.size();

        HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_DATA_LEN);
        struOutput.lpOutBuffer = ptrOutByte.getPointer();
        struOutput.dwOutBufferSize = HCNetSDK.ISAPI_DATA_LEN;

        HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_STATUS_LEN);
        struOutput.lpStatusBuffer = ptrStatusByte.getPointer();
        struOutput.dwStatusSize = HCNetSDK.ISAPI_STATUS_LEN;
        struOutput.write();

        if(!hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput))
        {
            System.out.println("GET /ISAPI/Intelligent/FDLib/1/picture failed with:" + lUserID + " "+ hCNetSDK.NET_DVR_GetLastError());
            return;
        }
        else
        {
            System.out.println("GET /ISAPI/Intelligent/FDLib/1/picture success");
            System.out.println("dwReturnXMLSize="+struOutput.dwReturnedXMLSize);
            logger.debug("删除人脸库图片成功 PID：" + m_picID);
        }
    }
    public void FDLibGetButtonClick(NativeLong lUserID) {
        try {
            if (m_isSupportFDLib)
            {
                boolean isQuit = false;
                int pos = 1;
                while (!isQuit)
                {
                    //返回true，说明支持人脸
                    int index = 0;
                    int id = m_FDLibList.get(index).dwFDID;

                    HCNetSDK.NET_DVR_XML_CONFIG_INPUT	struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
                    struInput.dwSize = struInput.size();

                    String str = "POST /ISAPI/Intelligent/FDLib/FCSearch\r\n";
                    HCNetSDK.BYTE_ARRAY ptrUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
                    System.arraycopy(str.getBytes(), 0, ptrUrl.byValue, 0, str.length());
                    ptrUrl.write();
                    struInput.lpRequestUrl = ptrUrl.getPointer();
                    struInput.dwRequestUrlLen = str.length();

                /*<FCSearchDescription version="2.0" xmlns="http://www.std-cgi.org/ver20/XMLSchema">
                    <searchID>C7AFBBC1-B3B0-0001-939C-1F00C53C17A9</searchID>
                    <searchResultPosition>1</searchResultPosition>
                    <maxResults>40</maxResults>   每次返回搜索结果的条数
                    <snapStartTime>2016-09-18T23:59:59Z</snapStartTime>  开始时间
                    <snapEndTime>2017-09-18T23:59:59Z</snapEndTime>      结束时间
                    <similarity>
                        <min>0</min>   相似度最小值
                        <max>80</max>  相似度最大值
                    </similarity>
                    <FDIDList>
                        <FDID>1</FDID> 人脸库ID
                    </FDIDList>
                </FCSearchDescription>*/
                    String strInBuffer = new String("<FCSearchDescription><searchID>C7AFBBC1-B3B0-0001-939C-1F00C53C17A9</searchID><searchResultPosition>" + pos + "</searchResultPosition><maxResults>100</maxResults><snapStartTime>2016-09-18T23:59:59Z</snapStartTime><snapEndTime>2017-09-18T23:59:59Z</snapEndTime><similarity><min>0</min><max>80</max></similarity><FDIDList><FDID>" + id + "</FDID></FDIDList></FCSearchDescription>");
                    HCNetSDK.BYTE_ARRAY ptrByte = new HCNetSDK.BYTE_ARRAY(10*HCNetSDK.BYTE_ARRAY_LEN);
                    ptrByte.byValue = strInBuffer.getBytes();
                    ptrByte.write();
                    struInput.lpInBuffer = ptrByte.getPointer();
                    struInput.dwInBufferSize = strInBuffer.length();
                    struInput.write();

                    HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
                    struOutput.dwSize = struOutput.size();

                    HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_DATA_LEN);
                    struOutput.lpOutBuffer = ptrOutByte.getPointer();
                    struOutput.dwOutBufferSize = HCNetSDK.ISAPI_DATA_LEN;

                    HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_STATUS_LEN);
                    struOutput.lpStatusBuffer = ptrStatusByte.getPointer();
                    struOutput.dwStatusSize = HCNetSDK.ISAPI_STATUS_LEN;
                    struOutput.write();

                    if (hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput))
                    {
                        String xmlStr = struOutput.lpOutBuffer.getString(0);

                        //dom4j解析xml
                        Document document = DocumentHelper.parseText(xmlStr);
                        //获取根节点元素对象
                        Element FCSearchResult = document.getRootElement();

                        //同时迭代当前节点下面的所有子节点
                        Iterator<Element> iterator = FCSearchResult.elementIterator();
                        while(iterator.hasNext())
                        {
                            Element e = iterator.next();
                            if (e.getName().equals("responseStatusStrg"))
                            {
                                if (e.getText().equals("MORE"))
                                {
                                    isQuit = false;
                                }
                                else
                                {
                                    isQuit = true;
                                }
                            }
                            if (e.getName().equals("numOfMatches"))
                            {
                                pos += Integer.parseInt(e.getText());
                                System.out.println("pos：" + pos);
                            }
                            if (e.getName().equals("totalMatches"))
                            {
                                if (isQuit)
                                {
                                    System.out.println("搜索到的总数是：" + e.getText());
                                }
                            }
                            if (e.getName().equals("MatchList"))
                            {
                                Iterator<Element> iterator2 = e.elementIterator();  //MatchElementList
                                while(iterator2.hasNext())
                                {
                                    Element e2 = iterator2.next();  //MatchElement
                                    Iterator<Element> iterator3 = e2.elementIterator();
                                    while (iterator3.hasNext())
                                    {
                                        Element e3 = iterator3.next();
                                        if (e3.getName().equals("snapPicURL"))
                                        {
                                            System.out.println("snapPicURL：" + e3.getText());
                                        }
                                        if (e3.getName().equals("facePicURL"))
                                        {
                                            System.out.println("facePicURL：" + e3.getText());
                                        }
                                    }

                                }

                            }

                        }
                    }
                    else
                    {
                        int code = hCNetSDK.NET_DVR_GetLastError();
                        logger.debug("获取失败: " + code);
                        return ;
                    }
                }
            }
            else
            {
                return ;
            }
        } catch (DocumentException ex) {
            logger.error("获取失败", ex);
            return;
        }
        return;
    }
}
