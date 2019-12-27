package com.jsc.hotspot.api.facade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.api.dto.FaceRecognitionInfo;
import com.jsc.hotspot.api.config.WebSocket;
import com.jsc.hotspot.api.facade.KafkaReceiverService;
import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.db.dao.*;
import com.jsc.hotspot.db.dao.ext.HotNumInfoEXTMapper;
import com.jsc.hotspot.db.dao.ext.RelatedNumEXTMapper;
import com.jsc.hotspot.db.domain.*;
import com.jsc.hotspot.db.po.RealatedNumAndCount;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.NewThreadAction;

import java.lang.reflect.InvocationTargetException;
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
@Service
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

    @Autowired
    private RelatedNumResultMapper relatedNumResultMapper;



    ExecutorService es= Executors.newFixedThreadPool(10);

    /**
     * 监听 "picTopic" 将图片数据存入文件系统和数据库
     * @param record
     */
    @Async
    @KafkaListener(topics = {"picTopic"})
    public void listen(ConsumerRecord<?, ?> record) throws InvocationTargetException, IllegalAccessException {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
             RelatedNumResultExample relatedNumResultExample=new RelatedNumResultExample();
             CameraCompareResultExample cameraCompareResultExample=new CameraCompareResultExample();
             HotNumInfoExample hotNumInfoExample=new HotNumInfoExample();
            FaceRecognitionInfo faceRecognitionInfo = new FaceRecognitionInfo();
            Object message = kafkaMessage.get();
            String msg = (String) message;//json字符串
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
            //把摄像头识别结果放入map 用于推送前台
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("regcognition", faceRecognitionInfo);

            CameraCatInfo cameraCatInfo = new CameraCatInfo();
            //1.中标
            if (faceRecognitionInfo.getCompareScore().compareTo(0.6)>0){

                cameraCatInfo.setQuality(1);
                cameraCatInfo.setTargetName(faceRecognitionInfo.getTargetName());

                CameraCompareResult result=new CameraCompareResult();
                BeanUtils.copyProperties(result,faceRecognitionInfo);
                result.setCreateTime(LocalDateTime.now());

                //2.添加人臉中標結果
                cameraCompareResultMapper.insertSelective(result);
                log.error("-------------------------成功添加人臉中標結果-------------");
                // 3.根据中标时间查询取号
                Callable<List> numberListCallable=new Callable<List>() {
                    @Override
                    public List call() throws Exception {
                        java.time.Duration duration = java.time.Duration.between(LocalDateTime.now() , captureTime );
                        long sleepTime=10000-duration.toMillis();
                        Thread.sleep(sleepTime);
                        log.error("------------------------------醒来-----------------------------------------");
                        LocalDateTime beforDate=captureTime.minusSeconds(30);
                        LocalDateTime afterDate=captureTime.plusSeconds(30);
                        HotNumInfoExample example=new HotNumInfoExample();
                        example.or().andCaptureTimeBetween(beforDate,afterDate);
                        log.error("############");
                        return hotNumInfoMapper.selectByExampleSelective(example);
                    }
                };

                FutureTask<List> numberListTask=new FutureTask<>(numberListCallable);
                es.submit(numberListTask);
                List<HotNumInfo> numberList= null;
                try {
                    //获取关联号码
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
                    //5.是否是第一次中标
                    CameraCompareResultExample.Criteria criteriaOfResult = cameraCompareResultExample.createCriteria();
                    criteriaOfResult.andTargetNameEqualTo(faceRecognitionInfo.getTargetName());
                    int count = (int) (cameraCompareResultMapper.countByExample(cameraCompareResultExample)) ;
                    //实例化一个对象承载关联结果
                    RelatedNumResult relatedNumResult=new RelatedNumResult();
                    relatedNumResult.setCaptureNum(count);
//                    if (count > 1) { //是否是第一次中标
                        //查询每个号码出现的频次比
                        List<RealatedNumAndCount> realatedNumAndCounts = relatedNumEXTMapper.getCampareValue(faceRecognitionInfo.getTargetName());
                        if (realatedNumAndCounts.size() > 0) {
                                //数组转json字符串
                                Map<String,Object> jsonMap= new HashMap<>();
                                jsonMap.put("topOne",realatedNumAndCounts.get(0));
                                jsonMap.put("topTwo",realatedNumAndCounts.get(1));
                                jsonMap.put("topThree",realatedNumAndCounts.get(2));
                                String  stringMap=JSON.toJSONString(jsonMap);
                                //json 字符串转object
                               JSONObject relatedResult=JSON.parseObject(stringMap);
                                map.put("realatedNumAndCount",relatedResult);
                                map.put("captureCount",count);
                                //更新关联结果
                                relatedNumResult.setRelatedResult(relatedResult);
                                relatedNumResultExample.or().andTargetNameEqualTo(result.getTargetName());
                                List<RelatedNumResult> list=relatedNumResultMapper.selectByExampleSelective(relatedNumResultExample);
                                if (list.size()>0) {
                                    RelatedNumResultExample.Criteria criteria = relatedNumResultExample.createCriteria();
                                    criteria.andTargetNameEqualTo(result.getTargetName());
                                    relatedNumResultMapper.updateByExampleSelective(relatedNumResult, relatedNumResultExample);
                                }else{
                                    relatedNumResult.setTargetName(result.getTargetName());
                                    relatedNumResult.setTargetFace(result.getTargetFaceStorageUrl());
                                    relatedNumResultMapper.insertSelective(relatedNumResult);
                                }

                    }

                    String data = JSON.toJSONString(map);
                        webSocket.sendOneMessage("admin123", data);
                }

            BeanUtils.copyProperties(cameraCatInfo,faceRecognitionInfo);
            cameraCatInfo.setCreateTime(LocalDateTime.now());
            //抓拍入库
            cameraCatInfoMapper.insertSelective(cameraCatInfo);



        }
    }
}
