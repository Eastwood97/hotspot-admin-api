package com.jsc.hotspot.api.facade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.api.dto.FaceRecognitionInfo;
import com.jsc.hotspot.api.config.WebSocket;
import com.jsc.hotspot.api.facade.KafkaReceiverService;
import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.db.dao.CameraCatInfoMapper;
import com.jsc.hotspot.db.dao.CameraCompareResultMapper;
import com.jsc.hotspot.db.dao.HotNumInfoMapper;
import com.jsc.hotspot.db.dao.RelatedNumMapper;
import com.jsc.hotspot.db.dao.ext.HotNumInfoEXTMapper;
import com.jsc.hotspot.db.dao.ext.RelatedNumEXTMapper;
import com.jsc.hotspot.db.domain.*;
import com.jsc.hotspot.db.po.CampareValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author huixing
 * @description kafka接收服务实现
 * @date 2019/11/4
 */
//@Service
public class KafkaReceiverServiceImpl implements KafkaReceiverService {

    private Log log= LogFactory.getLog(KafkaReceiverServiceImpl.class);
    @Autowired
    private WeedFSService weedFSService;

    @Autowired
    private HotNumInfoMapper hotNumInfoMapper;

    @Autowired
    private HotNumInfoEXTMapper hotNumInfoEXTMapper;

    @Autowired
    private RelatedNumMapper relatedNumMapper;

    @Autowired
    private RelatedNumEXTMapper relatedNumEXTMapper;

    @Autowired
    private CameraCompareResultMapper cameraCompareResultMapper;

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private CameraCatInfoMapper cameraCatInfoMapper;



    private CameraCompareResultExample cameraCompareResultExample=new CameraCompareResultExample();
    private HotNumInfoExample hotNumInfoExample=new HotNumInfoExample();
    private FaceRecognitionInfo faceRecognitionInfo=new FaceRecognitionInfo();

    ExecutorService es= Executors.newFixedThreadPool(10);

    /**
     * 监听 "picTopic" 将图片数据存入文件系统和数据库
     * @param record
     */
    @Async
    @KafkaListener(topics = {"picTopic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            String msg = (String) message;//json字符串
             log.error("---------------------------------1接受kafka消息："+msg+"-------------");
            JSONObject jsonObject = JSON.parseObject(msg);//json对象


            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String stringTime=jsonObject.getString("captureTime");
            LocalDateTime captureTime=LocalDateTime.parse(stringTime,df);
            faceRecognitionInfo.setCaptureTime(captureTime);
            faceRecognitionInfo.setCaptureFaceImg(jsonObject.getString("captureFaceImg"));
            faceRecognitionInfo.setCompareScore(jsonObject.getDoubleValue("compareScore"));
            faceRecognitionInfo.setDevId(jsonObject.getLongValue("devId"));
            faceRecognitionInfo.setLibraryName(jsonObject.getString("targetLibrary"));
            faceRecognitionInfo.setTargetFaceImg(jsonObject.getString("targetFaceImg"));
            faceRecognitionInfo.setTargetName(jsonObject.getString("targetName"));
            faceRecognitionInfo.setTargetFaceStorageUrl(jsonObject.getString("targetFaceStorageUrl"));
            faceRecognitionInfo.setDevIp(jsonObject.getString("devIp"));
            faceRecognitionInfo.setSceneImg(jsonObject.getString("sceneImg"));
            faceRecognitionInfo.setCaptureFaceStorageUrl(jsonObject.getString("captureFaceStorageUrl"));
            faceRecognitionInfo.setSceneStorageUrl(jsonObject.getString("sceneStorageUrl"));

            CameraCatInfo cameraCatInfo = new CameraCatInfo();
            //1.中标
            if (faceRecognitionInfo.getCompareScore().compareTo(0.6)==1){

                cameraCatInfo.setQuality(1);
                cameraCatInfo.setTargetName(faceRecognitionInfo.getTargetName());
                Map<String, Object> map = new HashMap<String, Object>();


            //    2.根据中标时间查询取号
                Callable<List> numberListCallable=new Callable<List>() {
                    @Override
                    public List call() throws Exception {
                        java.time.Duration duration = java.time.Duration.between(LocalDateTime.now() , captureTime );
                        long sleepTime=10000-duration.toMillis();
                        Thread.sleep(sleepTime);
                        log.error("------------------------------醒来-----------------------------------------");
                        return hotNumInfoEXTMapper.getIntervalNum(captureTime);

                    }
                };

                FutureTask<List> numberListTask=new FutureTask<>(numberListCallable);
                es.submit(numberListTask);
                List<HotNumInfo> numberList= null;
                try {
                    numberList = numberListTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                RelatedNum relatedNum = new RelatedNum();
                //3.存入关联表
                if (null!=numberList||numberList.size()>0){
                    for (HotNumInfo number : numberList
                    ) {
                        relatedNum.setImei(number.getImei());
                        relatedNum.setImsi(number.getImsi());
                        relatedNum.setIsdn(number.getIsdn());
                        relatedNum.setTargetName(faceRecognitionInfo.getTargetName());
                        relatedNum.setNumberId(number.getId());
                        relatedNumMapper.insertSelective(relatedNum);
                    }
                }



                //4.人脸中标结果入股

               CameraCompareResult result=new CameraCompareResult();
                result.setCaptureTime(faceRecognitionInfo.getCaptureTime());
                result.setCreateTime(LocalDateTime.now());
                result.setDevId(faceRecognitionInfo.getDevId());
                result.setTargetFaceImg(faceRecognitionInfo.getTargetFaceImg());
                result.setCaptureFaceImg(faceRecognitionInfo.getCaptureFaceImg());
                result.setCaptureFaceStorageUrl(faceRecognitionInfo.getCaptureFaceStorageUrl());
                result.setDevIp(faceRecognitionInfo.getDevIp());
                result.setSceneStorageUrl(faceRecognitionInfo.getSceneStorageUrl());
                result.setSceneImg(faceRecognitionInfo.getSceneImg());
                result.setLibraryName(faceRecognitionInfo.getLibraryName());
                result.setTargetFaceStorageUrl(faceRecognitionInfo.getTargetFaceStorageUrl());
                result.setTargetName(faceRecognitionInfo.getTargetName());
                result.setCompareScore(faceRecognitionInfo.getCompareScore());

                if(result.getCompareScore().compareTo(0.6)==1) {


                    cameraCompareResultMapper.insertSelective(result);
                    log.error("-------------------------添加成功-------------");

                    //5.是否是第一次中标
                    CameraCompareResultExample.Criteria criteriaOfResult = cameraCompareResultExample.createCriteria();
                    criteriaOfResult.andTargetNameEqualTo(faceRecognitionInfo.getTargetName());
                    int count = (int) (cameraCompareResultMapper.countByExample(cameraCompareResultExample)) + 1;
                    if (count > 1) {
                        //查询每个号码出现的频次比
                        List<CampareValue> campareValues = relatedNumEXTMapper.getCampareValue(faceRecognitionInfo.getTargetName());
                        if (campareValues.size() > 0) {
                            int top1 = campareValues.get(0).getCount();
                            if (top1 > 1) {
                                Map<Object, Integer> compareValue = new HashMap<Object, Integer>();
                                compareValue.put(campareValues.get(0), count);
                                compareValue.put(campareValues.get(1), count);
                                compareValue.put(campareValues.get(2), count);
                                map.put("compareValue", compareValue);
                            }
                        }
                    }
                    //推送识别和关联的结果
                    map.put("regcognition", faceRecognitionInfo);
                    String data = JSON.toJSONString(map);
       //             if (faceRecognitionInfo.getCompareScore().compareTo(0.6) == 1) {
                        System.out.println("++++++++++++++++++++++++++++++++++++++kafka的发送数据+++++++++++" + data);
                        webSocket.sendOneMessage("admin123", data);
       //             }
                }
            }

            cameraCatInfo.setCaptureTime(faceRecognitionInfo.getCaptureTime());
            cameraCatInfo.setDevId(faceRecognitionInfo.getDevId());
           cameraCatInfo.setCreateTime(LocalDateTime.now());
           cameraCatInfo.setDevIp(faceRecognitionInfo.getDevIp());
           cameraCatInfo.setCaptureFaceStorageUrl(faceRecognitionInfo.getCaptureFaceStorageUrl());
           cameraCatInfo.setCaptureFaceImg(faceRecognitionInfo.getCaptureFaceImg());
           cameraCatInfo.setSceneStorageUrl(faceRecognitionInfo.getSceneStorageUrl());
           cameraCatInfo.setSceneImg(faceRecognitionInfo.getSceneImg());
            //抓拍入库
            cameraCatInfoMapper.insertSelective(cameraCatInfo);



        }
    }
}
