package com.jsc.hotspot.accept.adapter.impl;

import com.alibaba.fastjson.JSON;
import com.jsc.hotspot.accept.adapter.HaiKDllInterfaceAdapter;
import com.jsc.hotspot.accept.dto.FaceRecognitionInfoDTO;
import com.jsc.hotspot.accept.facade.KafkaSender;
import com.jsc.hotspot.accept.facade.WeedFSService;
import com.jsc.hotspot.accept.sdk.HCNetSDK;
import com.jsc.hotspot.accept.utils.HTTPClientUtil;
import com.jsc.hotspot.common.bean.FileInfo;
import com.jsc.hotspot.common.bean.PlayBean;
import com.jsc.hotspot.common.bean.VideoDownLoadBean;
import com.jsc.hotspot.common.bean.VideoMessageBean;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.http.RestClient;
import com.jsc.hotspot.common.util.RandomNameUtil;
import com.jsc.hotspot.db.dao.TargetFaceCameraMapper;
import com.jsc.hotspot.db.domain.TargetFaceCamera;
import com.jsc.hotspot.db.domain.TargetFaceCameraExample;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Timer;

/**
/**
 * @author huixing
 * @description 海康dll动态库实现
 * @date 2019/12/3
 */
@Service("haiKDllAdapterImpl")
public class HaiKDllAdapterImpl implements HaiKDllInterfaceAdapter {
    private final Log logger = LogFactory.getLog(HaiKDllAdapterImpl.class);

    private HaiKDllFaceImpl faceInterface = new HaiKDllFaceImpl();

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    @Autowired
    private KafkaSender kafkaSenderService;

    @Autowired
    private WeedFSService weedFSService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private TargetFaceCameraMapper targetFaceCameraMapper;
    HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//设备登录信息
    HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//设备信息

    /**
     * 查看设备的能力集合
     */
    HCNetSDK.NET_DVR_XML_CONFIG_INPUT m_xmlConfigInputInfo = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
    HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT m_xmlConfigOutputInfo = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();

    FMSGCallBack fMSFCallBack;//报警回调函数实现
    FMSGCallBack_V31 fMSFCallBack_V31;//报警回调函数实现
    FGPSDataCallback fGpsCallBack;//GPS信息查询回调函数实现

    javax.swing.JTable jTableAlarm = new javax.swing.JTable();
    @Value("${camera1.ip}")
    String m_sDeviceIP;//已登录设备的IP地址
    @Value("${camera.username}")
    String m_sUsername;//设备用户名
    @Value("${camera.password}")
    String m_sPassword;//设备密码
    @Value("${camera.type}")
    int camera_type = 0;

    public int lUserID;//用户句柄
    public int lAlarmHandle;//报警布防句柄
    public int lListenHandle;//报警监听句柄

    NativeLong m_lPlayHandle;//播放句柄
    NativeLong m_lLoadHandle;//下载句柄

    int m_iChanShowNum;//回放通道
    boolean m_bSound;//是否开启声音
    boolean m_bPause;//是否已暂停
    boolean m_bTimeSave;//是否在保存

    Timer Downloadtimer;//下载用定时器
    Timer Playbacktimer;//回放用定时器

    @Autowired
    RestClient restClient;

    int dwCommand;
    int lChannel;
    Pointer lpInBuffer;
    int dwInBufferSize;

    public int getlUserID(){
        return lUserID;
    }

    @Override
    public BizResult<String> downLoadVideo(VideoDownLoadBean videoDownLoadBean) {
        if (m_lLoadHandle.intValue() == -1) {
            HCNetSDK.NET_DVR_TIME struStartTime;
            HCNetSDK.NET_DVR_TIME struStopTime;

            struStartTime = new HCNetSDK.NET_DVR_TIME();
            struStopTime = new HCNetSDK.NET_DVR_TIME();
            struStartTime.dwYear = videoDownLoadBean.getStartTime().getYear();//开始时间
            struStartTime.dwMonth = videoDownLoadBean.getStartTime().getMonthValue();
            struStartTime.dwDay = videoDownLoadBean.getStartTime().getDayOfMonth();
            struStartTime.dwHour = videoDownLoadBean.getStartTime().getHour();
            struStartTime.dwMinute = videoDownLoadBean.getStartTime().getMinute();
            struStartTime.dwSecond = videoDownLoadBean.getStartTime().getSecond();
            struStopTime.dwYear = videoDownLoadBean.getStopTime().getYear();//结束时间
            struStopTime.dwMonth = videoDownLoadBean.getStopTime().getMonthValue();
            struStopTime.dwDay = videoDownLoadBean.getStopTime().getDayOfMonth();
            struStopTime.dwHour = videoDownLoadBean.getStopTime().getHour();
            struStopTime.dwMinute = videoDownLoadBean.getStopTime().getMinute();
            struStopTime.dwSecond = videoDownLoadBean.getStopTime().getSecond();

            m_iChanShowNum = videoDownLoadBean.getChannelId();

            String sFileName = "c:/DownLoad/" + m_sDeviceIP + m_iChanShowNum + struStartTime.toStringTitle() + struStopTime.toStringTitle() + ".mp4";
            System.out.println(sFileName);
            m_lLoadHandle = hCNetSDK.NET_DVR_GetFileByTime(new NativeLong(lUserID), new NativeLong(m_iChanShowNum), struStartTime, struStopTime, sFileName);
            if (m_lLoadHandle.intValue() >= 0) {
                hCNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYSTART, 0, null);
                logger.debug("停止下载");
                Downloadtimer = new Timer();//新建定时器
                Downloadtimer.schedule(new DownloadTask((m_sDeviceIP + m_iChanShowNum + struStartTime.toStringTitle() + struStopTime.toStringTitle() + "")), 0, 5000);//0秒后开始响应函数

                //weedFSService.storagePic()
                BizResult<String> bizResult = BizResult.create("按时间下载成功");
                bizResult.setData((m_sDeviceIP + m_iChanShowNum + struStartTime.toStringTitle() + struStopTime.toStringTitle() + ""));
                return bizResult;
            } else {
                logger.error("按时间下载失败");
                BizResult<String> bizResult = BizResult.create("按时间下载失败");
                bizResult.setDesc("按时间下载失败");
                bizResult.setFlag(false);
                return bizResult;
            }
        } else {
            hCNetSDK.NET_DVR_StopGetFile(m_lLoadHandle);
            logger.debug("下载");
            Downloadtimer.cancel();
            return BizResult.create("按时间下载");
        }
    }

    public HaiKDllAdapterImpl(){

        lUserID = -1;
        lAlarmHandle = -1;
        lListenHandle = -1;
        fMSFCallBack = null;
        fMSFCallBack_V31 = null;
        fGpsCallBack = null;

        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc != true)
        {
            logger.info("初始化失败");
        }

        HCNetSDK.NET_DVR_LOCAL_GENERAL_CFG struGeneralCfg = new HCNetSDK.NET_DVR_LOCAL_GENERAL_CFG();
        struGeneralCfg.byAlarmJsonPictureSeparate =1; //控制JSON透传报警数据和图片是否分离，0-不分离，1-分离（分离后走COMM_ISAPI_ALARM回调返回）
        struGeneralCfg.write();

        if(!hCNetSDK.NET_DVR_SetSDKLocalCfg(17, struGeneralCfg.getPointer()))
        {
            logger.info("NET_DVR_SetSDKLocalCfg失败");
        }
    }
    @Override
    public void initdll() {
    }

    @Override
    public void exit() {
        if (lAlarmHandle > -1)
        {
            hCNetSDK.NET_DVR_CloseAlarmChan_V30(lAlarmHandle);
            lAlarmHandle = -1;
        }
        if (lUserID > -1) {
            //先注销
            hCNetSDK.NET_DVR_Logout(lUserID);
            lUserID = -1;
        }
        hCNetSDK.NET_DVR_Cleanup();
    }

    @Override
    public BizResult<String> register() {
        //注册之前先注销已注册的用户,预览情况下不可注销
        if (lUserID > -1) {
            //先注销
            hCNetSDK.NET_DVR_Logout(lUserID);
            lUserID = -1;
        }

        //注册

        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());

        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());

        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());

        m_strLoginInfo.wPort = 8000;

        m_strLoginInfo.bUseAsynLogin = false; //是否异步登录：0- 否，1- 是

        m_strLoginInfo.write();
        lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);

        if (lUserID == -1) {
            logger.info("注册失败");
            BizResult<String> bizResult = BizResult.create("注册失败");
            bizResult.setDesc("注册失败");
            bizResult.setFlag(false);
            return bizResult;
        } else {
            logger.info("注册成功");
            BizResult<String> bizResult = BizResult.create("注册成功");
            bizResult.setDesc("注册成功");
            bizResult.setFlag(true);
            return bizResult;
        }
    }

    public void Logout() {
        //报警撤防
        if (lAlarmHandle > -1)
        {
            if(!hCNetSDK.NET_DVR_CloseAlarmChan_V30(lAlarmHandle))
            {
            }
            else
            {
                lAlarmHandle = -1;
            }
        }

        //注销
        if (lUserID > -1) {
            if(hCNetSDK.NET_DVR_Logout(lUserID))
            {
                lUserID = -1;
            }
        }
    }

    public BizResult<String> SetupAlarmChan() {
        if (lUserID == -1)
        {
            logger.error("未注册");
            BizResult<String> bizResult = BizResult.create("未注册");
            bizResult.setData("注册失败");
            bizResult.setFlag(false);
            return bizResult;
        }
        if (lAlarmHandle < 0)//尚未布防,需要布防
        {
            if (fMSFCallBack_V31 == null)
            {
                fMSFCallBack_V31 = new FMSGCallBack_V31();
                Pointer pUser = null;
                if (!hCNetSDK.NET_DVR_SetDVRMessageCallBack_V31(fMSFCallBack_V31, pUser))
                {
                    System.out.println("设置回调函数失败!");
                }
            }
            HCNetSDK.NET_DVR_SETUPALARM_PARAM m_strAlarmInfo = new HCNetSDK.NET_DVR_SETUPALARM_PARAM();
            m_strAlarmInfo.dwSize=m_strAlarmInfo.size();
            m_strAlarmInfo.byLevel=1;//智能交通布防优先级：0- 一等级（高），1- 二等级（中），2- 三等级（低）
            m_strAlarmInfo.byAlarmInfoType=1;//智能交通报警信息上传类型：0- 老报警信息（NET_DVR_PLATE_RESULT），1- 新报警信息(NET_ITS_PLATE_RESULT)
            m_strAlarmInfo.byDeployType =1; //布防类型(仅针对门禁主机、人证设备)：0-客户端布防(会断网续传)，1-实时布防(只上传实时数据)
            m_strAlarmInfo.write();
            lAlarmHandle = hCNetSDK.NET_DVR_SetupAlarmChan_V41(lUserID, m_strAlarmInfo);
            if (lAlarmHandle == -1)
            {
                logger.error("布防失败，错误号:" +  hCNetSDK.NET_DVR_GetLastError());
                BizResult<String> bizResult = BizResult.create("布防失败");
                bizResult.setData("布防失败");
                bizResult.setFlag(false);
                return bizResult;
            }
            else
            {
                 logger.error("布防成功");
                BizResult<String> bizResult = BizResult.create("布防成功");
                bizResult.setData("布防成功");
                bizResult.setFlag(true);
                return bizResult;
            }
        }
        return BizResult.create("已布防");
    }

    public BizResult<Boolean> search(){

        return BizResult.create(faceInterface.SearchFDLib(new NativeLong(lUserID)));
    }

    @Override
    public void unregister() {
        lUserID = -1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BizResult<Boolean> deletePic(String targetIds) {
        ISAPILogin();
        String[] split=targetIds.split(",");
        for (String pid : split) {
            TargetFaceCameraExample targetFaceCamera = new TargetFaceCameraExample();
            TargetFaceCameraExample.Criteria criteria = targetFaceCamera.createCriteria();
            criteria.andPicStorageUrlEqualTo(pid);
            targetFaceCameraMapper.selectOneByExample(targetFaceCamera);
            targetFaceCameraMapper.deleteByExample(targetFaceCamera);
            boolean flag = faceInterface.deletePic(pid, new NativeLong(lUserID));
        }
        return BizResult.create(true);
    }

    /**
     * 上传文件到摄像机
     * @param fileInfo
     * @return
     */
    @Override
    public BizResult<Boolean> uploadToHaiKCamera(FileInfo fileInfo) {
        String urls = fileInfo.getUrl();
        Map<String, String> fileInfo1 = JSON.parseObject(urls, Map.class);
        // 开始上传数据
        if (!ObjectUtils.isEmpty(fileInfo1)) {
            fileInfo1.forEach((k, v) -> {
                if (v !="") {
                    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
                    String base64String = valueOperations.get(v);
                    if (base64String != null) {
                        byte[] picByte = Base64Utils.decodeFromString(base64String);
                        InputStream inputStream = new ByteArrayInputStream(picByte);
                        faceInterface.UploadFaceLinData(k, new NativeLong(lUserID), inputStream, fileInfo);
                    }
                }
            });
        }else {
            return BizResult.create(false);
        }
        return BizResult.create(true);
    }

    public void GetShortVideoFile(VideoDownLoadBean videoDownLoadBean){
//        Pointer pointer = null;
//        HCNetSDK.LPNET_DVR_PLAYCOND lpnet_dvr_playcond = new HCNetSDK.LPNET_DVR_PLAYCOND();
//        lpnet_dvr_playcond.byDrawFrame = 0;
//        lpnet_dvr_playcond.byCourseFile = 0;
//        lpnet_dvr_playcond.byStreamType = 0;
//        lpnet_dvr_playcond.dwChannel = 0;
//        HCNetSDK.NET_DVR_TIME struStartTime = new HCNetSDK.NET_DVR_TIME();
//        LocalDateTimeToNetDvrTime(videoDownLoadBean, struStartTime);
//        HCNetSDK.NET_DVR_TIME struStopTime = new HCNetSDK.NET_DVR_TIME();
//        LocalDateTimeToNetDvrTime(videoDownLoadBean, struStopTime);
//        lpnet_dvr_playcond.struStartTime = struStartTime;
//        lpnet_dvr_playcond.struStopTime = struStopTime;
//        hCNetSDK.NET_DVR_GetFileByTime_V40(lUserID, videoDownLoadBean.getStoreFileName(), lpnet_dvr_playcond);
    }

    @Override
    public void getLibrary() {
        faceInterface.GetFaceAppendData(new NativeLong(lUserID));
    }

    private void LocalDateTimeToNetDvrTime(VideoDownLoadBean videoDownLoadBean, HCNetSDK.NET_DVR_TIME struStartTime) {
        struStartTime.dwYear = videoDownLoadBean.getStartTime().getYear();
        struStartTime.dwMonth = videoDownLoadBean.getStartTime().getMonthValue();
        struStartTime.dwDay = videoDownLoadBean.getStartTime().getDayOfMonth();
        struStartTime.dwHour = videoDownLoadBean.getStartTime().getHour();
        struStartTime.dwMinute = videoDownLoadBean.getStartTime().getMinute();
        struStartTime.dwSecond = videoDownLoadBean.getStartTime().getSecond();
    }

    /**
     * 获取能力集
     */
    public void getCameraAbilities(){

    }

    /**
     * 获取文件上传的进度和状态
     */
    public void GetUploadState(){

    }

    /**
     * 停止文件上传
     */
    public void UploadClose() {

    }

    /**
     * 下载文件
     */
    public void StartDownload() {

    }

    /**
     * POST /ISAPI/Intelligent/FDLib/FDSearch
     */
    public void sarchLibraryPic(){

    }

    public class FMSGCallBack_V31 implements HCNetSDK.FMSGCallBack_V31
    {
        //报警信息回调函数
        public boolean invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser)
        {
            AlarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
            return true;
        }
    }

    public class FMSGCallBack implements HCNetSDK.FMSGCallBack
    {
        //报警信息回调函数
        public void invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser)
        {
            AlarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
        }
    }

    public class FGPSDataCallback implements HCNetSDK.fGPSDataCallback
    {
        public void invoke(int nHandle, int dwState, Pointer lpBuffer, int dwBufLen, Pointer pUser)
        {
        }
    }

    public void AlarmDataHandle(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser)
    {
        try {
            String sAlarmType = new String();
            DefaultTableModel alarmTableModel = ((DefaultTableModel) jTableAlarm.getModel());//获取表格模型
            String[] newRow = new String[3];
            //报警时间
            Date today = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String[] sIP = new String[2];

            sAlarmType = new String("lCommand=") + lCommand;
            //lCommand是传的报警类型
            switch (lCommand)
            {
                case HCNetSDK.COMM_ALARM_V40:
                    HCNetSDK.NET_DVR_ALARMINFO_V40 struAlarmInfoV40 = new HCNetSDK.NET_DVR_ALARMINFO_V40();
                    struAlarmInfoV40.write();
                    Pointer pInfoV40 = struAlarmInfoV40.getPointer();
                    pInfoV40.write(0, pAlarmInfo.getByteArray(0, struAlarmInfoV40.size()), 0, struAlarmInfoV40.size());
                    struAlarmInfoV40.read();

                    switch (struAlarmInfoV40.struAlarmFixedHeader.dwAlarmType)
                    {
                        case 0:
                            struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.setType(HCNetSDK.struIOAlarm.class);
                            struAlarmInfoV40.read();
                            sAlarmType = sAlarmType + new String("：信号量报警") + "，"+ "报警输入口：" + struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.struioAlarm.dwAlarmInputNo;
                            break;
                        case 1:
                            sAlarmType = sAlarmType + new String("：硬盘满");
                            break;
                        case 2:
                            sAlarmType = sAlarmType + new String("：信号丢失");
                            break;
                        case 3:
                            struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.setType(HCNetSDK.struAlarmChannel.class);
                            struAlarmInfoV40.read();
                            int iChanNum = struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.sstrualarmChannel.dwAlarmChanNum;
                            sAlarmType = sAlarmType + new String("：移动侦测") + "，"+ "报警通道个数：" + iChanNum + "，"+ "报警通道号：";

                            for (int i=0; i<iChanNum; i++)
                            {
                                byte[] byChannel = struAlarmInfoV40.pAlarmData.getByteArray(i*4, 4);

                                int iChanneNo = 0;
                                for(int j=0;j<4;j++)
                                {
                                    int ioffset = j*8;
                                    int iByte = byChannel[j]&0xff;
                                    iChanneNo = iChanneNo + (iByte << ioffset);
                                }

                                sAlarmType= sAlarmType + "+ch["+ iChanneNo +"]";
                            }

                            break;
                        case 4:
                            sAlarmType = sAlarmType + new String("：硬盘未格式化");
                            break;
                        case 5:
                            sAlarmType = sAlarmType + new String("：读写硬盘出错");
                            break;
                        case 6:
                            sAlarmType = sAlarmType + new String("：遮挡报警");
                            break;
                        case 7:
                            sAlarmType = sAlarmType + new String("：制式不匹配");
                            break;
                        case 8:
                            sAlarmType = sAlarmType + new String("：非法访问");
                            break;
                    }

                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);
                    break;
                case HCNetSDK.COMM_ALARM_V30:
                    HCNetSDK.NET_DVR_ALARMINFO_V30 strAlarmInfoV30 = new HCNetSDK.NET_DVR_ALARMINFO_V30();
                    strAlarmInfoV30.write();
                    Pointer pInfoV30 = strAlarmInfoV30.getPointer();
                    pInfoV30.write(0, pAlarmInfo.getByteArray(0, strAlarmInfoV30.size()), 0, strAlarmInfoV30.size());
                    strAlarmInfoV30.read();
                    switch (strAlarmInfoV30.dwAlarmType)
                    {
                        case 0:
                            sAlarmType = sAlarmType + new String("：信号量报警") + "，"+ "报警输入口：" + (strAlarmInfoV30.dwAlarmInputNumber+1);
                            break;
                        case 1:
                            sAlarmType = sAlarmType + new String("：硬盘满");
                            break;
                        case 2:
                            sAlarmType = sAlarmType + new String("：信号丢失");
                            break;
                        case 3:
                            sAlarmType = sAlarmType + new String("：移动侦测") + "，"+ "报警通道：";
                            for (int i=0; i<64; i++)
                            {
                                if (strAlarmInfoV30.byChannel[i] == 1)
                                {
                                    sAlarmType=sAlarmType + "ch"+(i+1)+" ";
                                }
                            }
                            break;
                        case 4:
                            sAlarmType = sAlarmType + new String("：硬盘未格式化");
                            break;
                        case 5:
                            sAlarmType = sAlarmType + new String("：读写硬盘出错");
                            break;
                        case 6:
                            sAlarmType = sAlarmType + new String("：遮挡报警");
                            break;
                        case 7:
                            sAlarmType = sAlarmType + new String("：制式不匹配");
                            break;
                        case 8:
                            sAlarmType = sAlarmType + new String("：非法访问");
                            break;
                    }
                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);
                    break;
                case HCNetSDK.COMM_ALARM_RULE:
                    HCNetSDK.NET_VCA_RULE_ALARM strVcaAlarm = new HCNetSDK.NET_VCA_RULE_ALARM();
                    strVcaAlarm.write();
                    Pointer pVcaInfo = strVcaAlarm.getPointer();
                    pVcaInfo.write(0, pAlarmInfo.getByteArray(0, strVcaAlarm.size()), 0, strVcaAlarm.size());
                    strVcaAlarm.read();

                    switch (strVcaAlarm.struRuleInfo.wEventTypeEx)
                    {
                        case 1:
                            sAlarmType = sAlarmType + new String("：穿越警戒面") + "，" +
                                    "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                    "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                    "_byIvmsChannel:" +  strVcaAlarm.struDevInfo.byIvmsChannel +
                                    "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                            break;
                        case 2:
                            sAlarmType = sAlarmType + new String("：目标进入区域") + "，" +
                                    "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                    "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                    "_byIvmsChannel:" +  strVcaAlarm.struDevInfo.byIvmsChannel +
                                    "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                            break;
                        case 3:
                            sAlarmType = sAlarmType + new String("：目标离开区域") + "，" +
                                    "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                    "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                    "_byIvmsChannel:" +  strVcaAlarm.struDevInfo.byIvmsChannel +
                                    "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                            break;
                        default:
                            sAlarmType = sAlarmType + new String("：其他行为分析报警，事件类型：")
                                    + strVcaAlarm.struRuleInfo.wEventTypeEx +
                                    "_wPort:" + strVcaAlarm.struDevInfo.wPort +
                                    "_byChannel:" + strVcaAlarm.struDevInfo.byChannel +
                                    "_byIvmsChannel:" +  strVcaAlarm.struDevInfo.byIvmsChannel +
                                    "_Dev IP：" + new String(strVcaAlarm.struDevInfo.struDevIP.sIpV4);
                            break;
                    }
                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);

                    if(strVcaAlarm.dwPicDataLen>0)
                    {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            fout = new FileOutputStream(".\\pic\\"+ new String(pAlarmer.sDeviceIP).trim()
                                    + "wEventTypeEx[" + strVcaAlarm.struRuleInfo.wEventTypeEx + "]_"+ newName +"_vca.jpg");
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strVcaAlarm.pImage.getByteBuffer(offset, strVcaAlarm.dwPicDataLen);
                            byte [] bytes = new byte[strVcaAlarm.dwPicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        }catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case HCNetSDK.COMM_ALARM_PDC:
                    HCNetSDK.NET_DVR_PDC_ALRAM_INFO strPDCResult = new HCNetSDK.NET_DVR_PDC_ALRAM_INFO();
                    strPDCResult.write();
                    Pointer pPDCInfo = strPDCResult.getPointer();
                    pPDCInfo.write(0, pAlarmInfo.getByteArray(0, strPDCResult.size()), 0, strPDCResult.size());
                    strPDCResult.read();

                    if(strPDCResult.byMode == 0)
                    {
                        strPDCResult.uStatModeParam.setType(HCNetSDK.NET_DVR_STATFRAME.class);
                        sAlarmType = sAlarmType + "：客流量统计，进入人数："+ strPDCResult.dwEnterNum + "，离开人数：" + strPDCResult.dwLeaveNum +
                                ", byMode:" + strPDCResult.byMode + ", dwRelativeTime:" + strPDCResult.uStatModeParam.struStatFrame.dwRelativeTime +
                                ", dwAbsTime:" + strPDCResult.uStatModeParam.struStatFrame.dwAbsTime;
                    }
                    if(strPDCResult.byMode == 1)
                    {
                        strPDCResult.uStatModeParam.setType(HCNetSDK.NET_DVR_STATTIME.class);
                        String strtmStart = "" + String.format("%04d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwYear) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwMonth) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwDay) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwHour) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwMinute) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmStart.dwSecond);
                        String strtmEnd = "" + String.format("%04d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwYear) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwMonth) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwDay) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwHour) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwMinute) +
                                String.format("%02d", strPDCResult.uStatModeParam.struStatTime.tmEnd.dwSecond);
                        sAlarmType = sAlarmType + "：客流量统计，进入人数："+ strPDCResult.dwEnterNum + "，离开人数：" + strPDCResult.dwLeaveNum +
                                ", byMode:" + strPDCResult.byMode + ", tmStart:" + strtmStart + ",tmEnd :" + strtmEnd;
                    }

                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(strPDCResult.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);
                    break;
                case HCNetSDK.COMM_UPLOAD_FACESNAP_RESULT:
                    //实时人脸抓拍上传
                    HCNetSDK.NET_VCA_FACESNAP_RESULT strFaceSnapInfo = new HCNetSDK.NET_VCA_FACESNAP_RESULT();
                    strFaceSnapInfo.write();
                    Pointer pFaceSnapInfo = strFaceSnapInfo.getPointer();
                    pFaceSnapInfo.write(0, pAlarmInfo.getByteArray(0, strFaceSnapInfo.size()), 0, strFaceSnapInfo.size());
                    strFaceSnapInfo.read();
                    sAlarmType = sAlarmType + "：人脸抓拍上传，人脸评分：" + strFaceSnapInfo.dwFaceScore + "，年龄段：" + strFaceSnapInfo.struFeature.byAgeGroup + "，性别：" + strFaceSnapInfo.struFeature.bySex;
                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(strFaceSnapInfo.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); //设置日期格式
                    String time = df.format(new Date()); // new Date()为获取当前系统时间
                    //人脸图片写文件
//                    try {
//                        FileOutputStream small = new FileOutputStream(System.getProperty("user.dir") + "\\pic\\" + time + "small.jpg");
//                        FileOutputStream big = new FileOutputStream(System.getProperty("user.dir") + "\\pic\\" + time + "big.jpg");
//
                    LocalDateTime localDateTime1 = LocalDateTime.now();
                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String captureTime1 = formatter1.format(localDateTime1);
                        FaceRecognitionInfoDTO faceRecognitionInfoFace = new FaceRecognitionInfoDTO();
                        if(strFaceSnapInfo.dwFacePicLen > 0)
                        {
//                            try {
                                byte[] facePicBytes = strFaceSnapInfo.pBuffer1.getByteArray(0, strFaceSnapInfo.dwFacePicLen);

                            faceRecognitionInfoFace.setCaptureTime(captureTime1);
                                ByteArrayInputStream inputStream = new ByteArrayInputStream(facePicBytes);
                                BizResult<String> bizResult = weedFSService.storagePic(inputStream);

                                if (bizResult.getFlag()){
                                    faceRecognitionInfoFace.setCaptureFaceStorageUrl(bizResult.getData());
                                }
                                if (logger.isDebugEnabled()){
                                    logger.debug("HaiKDllAdapterImpl: 人脸信息：{0}, {1}" +bizResult.getData() + inputStream);
                                }
//                                small.write(strFaceSnapInfo.pBuffer1.getByteArray(0, strFaceSnapInfo.dwFacePicLen), 0, strFaceSnapInfo.dwFacePicLen);
//                                small.close();
//                            } catch (IOException ex) {
//                                logger.error(HaiKDllAdapterImpl.class.getName() + "  COMM_UPLOAD_FACESNAP_RESULT");
//                            }

                        }
                        if(strFaceSnapInfo.dwFacePicLen > 0)
                        {
//                            try {
                                byte[] scenePicBytes = strFaceSnapInfo.pBuffer2.getByteArray(0, strFaceSnapInfo.dwBackgroundPicLen);
                            faceRecognitionInfoFace.setCaptureTime(captureTime1);

                                ByteArrayInputStream inputStream = new ByteArrayInputStream(scenePicBytes);
                                BizResult<String> bizResult = weedFSService.storagePic(inputStream);
                                if (bizResult.getFlag()){
                                    faceRecognitionInfoFace.setSceneStorageUrl(bizResult.getData());
                                }
                                if (logger.isDebugEnabled()){
                                    logger.debug("HaiKDllAdapterImpl: 人脸信息：{0}, {1}" +bizResult.getData() + inputStream);
                                }
//                                big.write(strFaceSnapInfo.pBuffer2.getByteArray(0, strFaceSnapInfo.dwBackgroundPicLen), 0, strFaceSnapInfo.dwBackgroundPicLen);
//                                big.close();
//                            } catch (IOException ex) {
//                                logger.error(HaiKDllAdapterImpl.class.getName() + "  COMM_UPLOAD_FACESNAP_RESULT");
//                            }
                        }
                    kafkaSenderService.send(JSON.toJSONString(faceRecognitionInfoFace));
//                    } catch (FileNotFoundException ex) {
//                        logger.error(HaiKDllAdapterImpl.class.getName() + "  COMM_UPLOAD_FACESNAP_RESULT");
//                    }
                    break;
                case HCNetSDK.COMM_SNAP_MATCH_ALARM:
                    //人脸黑名单比对报警
                    LocalDateTime localDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String captureTime = formatter.format(localDateTime);
                    FaceRecognitionInfoDTO faceRecognitionInfo = new FaceRecognitionInfoDTO();
                    HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM strFaceSnapMatch = new HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM();
                    strFaceSnapMatch.write();
                    Pointer pFaceSnapMatch = strFaceSnapMatch.getPointer();
                    pFaceSnapMatch.write(0, pAlarmInfo.getByteArray(0, strFaceSnapMatch.size()), 0, strFaceSnapMatch.size());
                    strFaceSnapMatch.read();

                    if (strFaceSnapMatch.dwSnapPicLen > 0 && strFaceSnapMatch.pSnapPicBuffer != null) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strFaceSnapMatch.pSnapPicBuffer.getByteBuffer(offset, strFaceSnapMatch.dwSnapPicLen);
                        byte[] bytes = new byte[strFaceSnapMatch.dwSnapPicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        // 场景图
//                        String srString = new String(bytes);
//                        System.out.println(srString);
//                        faceRecognitionInfo.setSceneImg(srString);

                        ISAPILogin();
                        VideoDownLoadBean videoDownLoadBean = new VideoDownLoadBean();
                        videoDownLoadBean.setStartTime(localDateTime.minusSeconds(5));
                        videoDownLoadBean.setStopTime(localDateTime.minusSeconds(5));
                        int fileName = RandomNameUtil.getNum(0, Integer.MAX_VALUE);
                        videoDownLoadBean.setStoreFileName("D:\\Video\\" + fileName);
                        // 下载黑名单前后5s的小视频
                        GetShortVideoFile(videoDownLoadBean);
                        byte[] strOut;
                        try {
//                            strOut= HTTPClientUtil.doGet(srString, null);
                            InputStream inputStream = new ByteArrayInputStream(bytes);
                            BizResult<String> bizResult = weedFSService.storagePic(inputStream);
                            if (bizResult.getFlag()){
                                faceRecognitionInfo.setSceneStorageUrl(bizResult.getData());
                            }
                            if (logger.isDebugEnabled()){
                                logger.debug("HaiKDllAdapterImpl: 场景图信息：{0}, {1}" + bizResult.getData() + inputStream);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    if (strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen > 0 && strFaceSnapMatch.struBlackListInfo.pBuffer1 != null) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strFaceSnapMatch.struBlackListInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen);
                        byte[] bytes = new byte[strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        // 黑名单
//                        String srString = new String(bytes);
//                        System.out.println(srString);
//                        faceRecognitionInfo.setTargetFaceImg(srString);
                        byte[] strOut;
                        try {
//                            strOut= HTTPClientUtil.doGet(srString, null);
                            InputStream inputStream = new ByteArrayInputStream(bytes);
                            weedFSService.init();
                            BizResult<String> bizResult = weedFSService.storagePic(inputStream);
                            if (bizResult.getFlag()){
                                faceRecognitionInfo.setTargetFaceStorageUrl(bizResult.getData());
                            }
                            if (logger.isDebugEnabled()){
                                logger.debug("HaiKDllAdapterImpl: 黑名单人脸信息：{0}, {1}" +bizResult.getData() + inputStream);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen > 0 && strFaceSnapMatch.struSnapInfo.pBuffer1 != null) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strFaceSnapMatch.struSnapInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen);
                        byte[] bytes = new byte[strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        // 抓拍人脸
//                        String srString = new String(bytes);
//                        faceRecognitionInfo.setCaptureFaceImg(srString);

                        byte[] strOut;
                        try {



                            LocalDateTime localDateTime2 = LocalDateTime.now();
                            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            String captureTime2 = formatter2.format(localDateTime2);
                            faceRecognitionInfo.setCaptureTime(captureTime2);
                            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                            BizResult<String> bizResult = weedFSService.storagePic(inputStream);

                            if (bizResult.getFlag()){
                                faceRecognitionInfo.setCaptureFaceStorageUrl(bizResult.getData());
                            }
                            if (logger.isDebugEnabled()){
                                logger.debug("HaiKDllAdapterImpl: 人脸信息：{0}, {1}" +bizResult.getData() + inputStream);
                            }






//                            strOut= HTTPClientUtil.doGet(srString, null);
//                            InputStream inputStream = new ByteArrayInputStream(strOut);
//                            BizResult<String> bizResult = weedFSService.storagePic(inputStream);
//                            if (bizResult.getFlag()){
//                                faceRecognitionInfo.setCaptureFaceStorageUrl(bizResult.getData());
//                            }
//                            if (logger.isDebugEnabled()){
//                                logger.debug("HaiKDllAdapterImpl: 抓拍人脸信息：{0}, {1}" +bizResult.getData() + inputStream);
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        InputStream in = restClient.get("http://192.168.1.100:80", "picture/Streaming/tracks/103/?name=ch0001_00010000067005405235200435403&size=435403", InputStream.class);
//                        FileOutputStream downloadFile = null;
//                        try {
//                            int index;
//                            downloadFile = new FileOutputStream("D:\\pic\\1.jpg");
//                            while ((index = in.read(bytes)) != -1) {
//                                downloadFile.write(bytes, 0, index);
//                                downloadFile.flush();
//                            }
//                            downloadFile.close();
//                            in.close();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }

                    }
                    if ((strFaceSnapMatch.dwSnapPicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_pSnapPicBuffer" + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strFaceSnapMatch.pSnapPicBuffer.getByteBuffer(offset, strFaceSnapMatch.dwSnapPicLen);
                            byte[] bytes = new byte[strFaceSnapMatch.dwSnapPicLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            // 场景图的URL地址


                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if ((strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_struSnapInfo_pBuffer1" + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strFaceSnapMatch.struSnapInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen);
                            byte[] bytes = new byte[strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if ((strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = System.getProperty("user.dir") + "\\pic\\" + newName + "_fSimilarity_" + strFaceSnapMatch.fSimilarity + "_struBlackListInfo_pBuffer1" + ".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strFaceSnapMatch.struBlackListInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen);
                            byte[] bytes = new byte[strFaceSnapMatch.struBlackListInfo.dwBlackListPicLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    sAlarmType = sAlarmType + "：人脸黑名单比对报警，相识度：" + strFaceSnapMatch.fSimilarity + "，黑名单姓名：" + new String(strFaceSnapMatch.struBlackListInfo.struBlackListInfo.struAttribute.byName, "GBK").trim() + "，\n黑名单证件信息：" + new String(strFaceSnapMatch.struBlackListInfo.struBlackListInfo.struAttribute.byCertificateNumber).trim();
                    String targetName = new String(strFaceSnapMatch.struBlackListInfo.struBlackListInfo.struAttribute.byName, "GBK").trim();
                    float fSimilarity = strFaceSnapMatch.fSimilarity;
                    faceRecognitionInfo.setTargetName(targetName);
                    faceRecognitionInfo.setCompareScore(fSimilarity);
                    //获取人脸库ID
                    byte[] FDIDbytes;
                    String libraryId = "";
                    if ((strFaceSnapMatch.struBlackListInfo.dwFDIDLen > 0) && (strFaceSnapMatch.struBlackListInfo.pFDID != null)) {
                        ByteBuffer FDIDbuffers = strFaceSnapMatch.struBlackListInfo.pFDID.getByteBuffer(0, strFaceSnapMatch.struBlackListInfo.dwFDIDLen);
                        FDIDbytes = new byte[strFaceSnapMatch.struBlackListInfo.dwFDIDLen];
                        FDIDbuffers.rewind();
                        FDIDbuffers.get(FDIDbytes);
                        sAlarmType = sAlarmType + "，人脸库ID:" + new String(FDIDbytes).trim();
                         libraryId = new String(FDIDbytes).trim();

                    }
                    //获取人脸图片ID
                    byte[] PIDbytes;
                    if ((strFaceSnapMatch.struBlackListInfo.dwPIDLen > 0) && (strFaceSnapMatch.struBlackListInfo.pPID != null)) {
                        ByteBuffer PIDbuffers = strFaceSnapMatch.struBlackListInfo.pPID.getByteBuffer(0, strFaceSnapMatch.struBlackListInfo.dwPIDLen);
                        PIDbytes = new byte[strFaceSnapMatch.struBlackListInfo.dwPIDLen];
                        PIDbuffers.rewind();
                        PIDbuffers.get(PIDbytes);
                        sAlarmType = sAlarmType + "，人脸图片ID:" + new String(PIDbytes).trim();
                    }
                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    faceRecognitionInfo.setDevIp(sIP[0]);
                    faceRecognitionInfo.setTargetLibrary(libraryId);
                    faceRecognitionInfo.setCaptureTime(captureTime);
                    if (logger.isDebugEnabled()){
                        logger.debug("HaiKDllAdapterImpl: 已上传到文件系统：" + faceRecognitionInfo.toString());
                    }
                    kafkaSenderService.send(JSON.toJSONString(faceRecognitionInfo));
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);
                    break;
                case HCNetSDK.COMM_ALARM_ACS: //门禁主机报警信息
                    HCNetSDK.NET_DVR_ACS_ALARM_INFO strACSInfo = new HCNetSDK.NET_DVR_ACS_ALARM_INFO();
                    strACSInfo.write();
                    Pointer pACSInfo = strACSInfo.getPointer();
                    pACSInfo.write(0, pAlarmInfo.getByteArray(0, strACSInfo.size()), 0, strACSInfo.size());
                    strACSInfo.read();

                    sAlarmType = sAlarmType + "：门禁主机报警信息，卡号："+  new String(strACSInfo.struAcsEventInfo.byCardNo).trim() + "，卡类型：" +
                            strACSInfo.struAcsEventInfo.byCardType + "，报警主类型：" + strACSInfo.dwMajor + "，报警次类型：" + strACSInfo.dwMinor;

                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);

                    if(strACSInfo.dwPicDataLen>0)
                    {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = ".\\pic\\"+ new String(pAlarmer.sDeviceIP).trim() +
                                    "_byCardNo["+ new String(strACSInfo.struAcsEventInfo.byCardNo).trim() +
                                    "_"+ newName + "_Acs.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strACSInfo.pPicData.getByteBuffer(offset, strACSInfo.dwPicDataLen);
                            byte [] bytes = new byte[strACSInfo.dwPicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case HCNetSDK.COMM_ID_INFO_ALARM: //身份证信息
                    HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM strIDCardInfo = new HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM();
                    strIDCardInfo.write();
                    Pointer pIDCardInfo = strIDCardInfo.getPointer();
                    pIDCardInfo.write(0, pAlarmInfo.getByteArray(0, strIDCardInfo.size()), 0, strIDCardInfo.size());
                    strIDCardInfo.read();

                    sAlarmType = sAlarmType + "：门禁身份证刷卡信息，身份证号码："+  new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() + "，姓名：" +
                            new String(strIDCardInfo.struIDCardCfg.byName).trim() + "，报警主类型：" + strIDCardInfo.dwMajor + "，报警次类型：" + strIDCardInfo.dwMinor;

                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);

                    //身份证图片
                    if(strIDCardInfo.dwPicDataLen>0)
                    {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = ".\\pic\\"+ new String(pAlarmer.sDeviceIP).trim() +
                                    "_byCardNo["+ new String(strIDCardInfo.struIDCardCfg.byIDNum ).trim() +
                                    "_"+ newName + "_IDInfoPic.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strIDCardInfo.pPicData.getByteBuffer(offset, strIDCardInfo.dwPicDataLen);
                            byte [] bytes = new byte[strIDCardInfo.dwPicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    //抓拍图片
                    if(strIDCardInfo.dwCapturePicDataLen >0)
                    {
                        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                        String newName = sf.format(new Date());
                        FileOutputStream fout;
                        try {
                            String filename = ".\\pic\\"+ new String(pAlarmer.sDeviceIP).trim() +
                                    "_byCardNo["+ new String(strIDCardInfo.struIDCardCfg.byIDNum ).trim() +
                                    "_"+ newName + "_IDInfoCapturePic.jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = strIDCardInfo.pCapturePicData.getByteBuffer(offset, strIDCardInfo.dwCapturePicDataLen);
                            byte [] bytes = new byte[strIDCardInfo.dwCapturePicDataLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case HCNetSDK.COMM_ISAPI_ALARM: //ISAPI协议报警信息
                    HCNetSDK.NET_DVR_ALARM_ISAPI_INFO struEventISAPI = new HCNetSDK.NET_DVR_ALARM_ISAPI_INFO();
                    struEventISAPI.write();
                    Pointer pEventISAPI = struEventISAPI.getPointer();
                    pEventISAPI.write(0, pAlarmInfo.getByteArray(0, struEventISAPI.size()), 0, struEventISAPI.size());
                    struEventISAPI.read();

                    sAlarmType = sAlarmType + "：ISAPI协议报警信息, 数据格式:" + struEventISAPI.byDataType +
                            ", 图片个数:" + struEventISAPI.byPicturesNumber;

                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);

                    SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                    String curTime = sf1.format(new Date());
                    FileOutputStream foutdata;
                    try {
                        String jsonfilename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime +"_ISAPI_Alarm_" + ".json";
                        foutdata = new FileOutputStream(jsonfilename);
                        //将字节写入文件
                        ByteBuffer jsonbuffers = struEventISAPI.pAlarmData.getByteBuffer(0, struEventISAPI.dwAlarmDataLen);
                        byte [] jsonbytes = new byte[struEventISAPI.dwAlarmDataLen];
                        jsonbuffers.rewind();
                        jsonbuffers.get(jsonbytes);
                        foutdata.write(jsonbytes);
                        foutdata.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    for(int i=0;i<struEventISAPI.byPicturesNumber;i++)
                    {
                        HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA struPicData = new HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA();
                        struPicData.write();
                        Pointer pPicData = struPicData.getPointer();
                        pPicData.write(0, struEventISAPI.pPicPackData.getByteArray(i*struPicData.size(), struPicData.size()), 0, struPicData.size());
                        struPicData.read();

                        FileOutputStream fout;
                        try {
                            String filename = ".\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime +
                                    "_ISAPIPic_"+ i + "_" + new String(struPicData.szFilename).trim() +".jpg";
                            fout = new FileOutputStream(filename);
                            //将字节写入文件
                            long offset = 0;
                            ByteBuffer buffers = struPicData.pPicData.getByteBuffer(offset, struPicData.dwPicLen);
                            byte [] bytes = new byte[struPicData.dwPicLen];
                            buffers.rewind();
                            buffers.get(bytes);
                            fout.write(bytes);
                            fout.close();
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    newRow[0] = dateFormat.format(today);
                    //报警类型
                    newRow[1] = sAlarmType;
                    //报警设备IP地址
                    sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                    newRow[2] = sIP[0];
                    alarmTableModel.insertRow(0, newRow);
                    break;
            }
        } catch (UnsupportedEncodingException ex) {
            logger.error(HaiKDllAdapterImpl.class.getName() + "  AlarmDataHandle");
        }
    }

    /**
     * ISAPI 协议透传认证
     */
    private void ISAPILogin() {
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("admin",
                "Admin12345"); //设备登录用户名密码，摘要认证方式
        HTTPClientUtil.client.getState().setCredentials(AuthScope.ANY, creds);
    }

    /**
     * 视频下载接口
     */
    public void downLoadVideo(PlayBean playBean) {

    }

    /**
     * 下载视频定时器类
     */
    class DownloadTask extends java.util.TimerTask
    {
        private String id;
        public DownloadTask(String id){
            this.id = id;
        }
        //定时器函数
        @Override
        public void run()
        {
            IntByReference nPos = new IntByReference(0);
            hCNetSDK.NET_DVR_PlayBackControl(m_lLoadHandle, HCNetSDK.NET_DVR_PLAYGETPOS, 0, nPos);
            if (nPos.getValue() > 100)
            {
                hCNetSDK.NET_DVR_StopGetFile(m_lLoadHandle);
                m_lLoadHandle.setValue(-1);
                logger.debug("下载");
                Downloadtimer.cancel();
                logger.error("由于网络原因或DVR忙,下载异常终止!");
                VideoMessageBean videoMessageBean = new VideoMessageBean();
                videoMessageBean.setId(id);
                videoMessageBean.setStorageId("");
                kafkaSenderService.sendVideoId(JSON.toJSONString(videoMessageBean));
            }
            if (nPos.getValue() == 100)
            {
                hCNetSDK.NET_DVR_StopGetFile(m_lLoadHandle);
                m_lLoadHandle.setValue(-1);
                logger.debug("下载");
                Downloadtimer.cancel();
                logger.debug("按时间下载结束!");
                VideoMessageBean videoMessageBean = new VideoMessageBean();
                BizResult<String> bizResult = null;
                try {
                    bizResult = weedFSService.storagePic(new FileInputStream("c:/DownLoad/"+id));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 下载完成放入Kafka
                videoMessageBean.setId(id);
                videoMessageBean.setStorageId(bizResult.getData());
                kafkaSenderService.sendVideoId(JSON.toJSONString(videoMessageBean));
            }
        }
    }

}
