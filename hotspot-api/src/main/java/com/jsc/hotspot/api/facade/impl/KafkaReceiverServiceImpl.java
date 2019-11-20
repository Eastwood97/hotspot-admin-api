package com.jsc.hotspot.api.facade.impl;

import com.alibaba.fastjson.JSON;
import com.jsc.hotspot.accept.dto.AcceptBean;
import com.jsc.hotspot.accept.dto.FaceRecognitionInfo;
import com.jsc.hotspot.api.facade.KafkaReceiverService;
import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.db.dao.CameraCompareResultMapper;
import com.jsc.hotspot.db.dao.HotNumInfoMapper;
import com.jsc.hotspot.db.dao.RelatedNumMapper;
import com.jsc.hotspot.db.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author huixing
 * @description kafka接收服务实现
 * @date 2019/11/4
 */
//@Service
@Slf4j
public class KafkaReceiverServiceImpl implements KafkaReceiverService {
    @Autowired
    private WeedFSService weedFSService;

    @Autowired
    private HotNumInfoMapper hotNumInfoMapper;

    @Autowired
    private HotNumInfoExample hotNumInfoExample;

    @Autowired
    private RelatedNumMapper relatedNumMapper;

    @Autowired
    private CameraCompareResultMapper cameraCompareResultMapper;

    @Autowired
    private CameraCompareResultExample cameraCompareResultExample;

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
            String msg = (String)message;//json字符串
            Object object= JSON.parseObject(msg);//json对象
            FaceRecognitionInfo faceRecognitionInfo= (FaceRecognitionInfo) object;

            //1.中标
            if (faceRecognitionInfo.getCompareScore()==0){
                Map<String,Object> map=new HashMap<String,Object>();
                //2.根据中标时间查询取号
             HotNumInfoExample.Criteria criteria= hotNumInfoExample.createCriteria();
             criteria.andCaptureTimeEqualTo(faceRecognitionInfo.getCaptureTime());
             List<HotNumInfo>  numberList=hotNumInfoMapper.selectByExample(hotNumInfoExample);
                RelatedNum relatedNum=new RelatedNum();
             //3.存入关联表
             for (HotNumInfo number:numberList
                     ) {
               relatedNum.setImei(number.getImei());
               relatedNum.setImsi(number.getImsi());
               relatedNum.setIsdn(number.getIsdn());
               relatedNum.setFileName(faceRecognitionInfo.getTargetFaceImg());
               relatedNum.setNumberId(number.getId());
               relatedNumMapper.insertSelective(relatedNum);
                }
             //4.人脸中标结果入股

               CameraCompareResult result=new CameraCompareResult();
                result.setCaptureTime(faceRecognitionInfo.getCaptureTime());
                result.setCreateTime(LocalDateTime.now());
                result.setUpdateTime(LocalDateTime.now());
                result.setDevId(faceRecognitionInfo.getDevId());
                result.setTargetFaceImg(faceRecognitionInfo.getTargetFaceImg());
                result.setCaptureFaceImg(faceRecognitionInfo.getCaptureFaceImg());
                cameraCompareResultMapper.insertSelective(result);
                //5.是否是第一次中标
                CameraCompareResultExample.Criteria criteriaOfResult=cameraCompareResultExample.createCriteria();
                criteriaOfResult.andCaptureFaceImgEqualTo(faceRecognitionInfo.getCaptureFaceImg());
                int count= (int) (cameraCompareResultMapper.countByExample(cameraCompareResultExample))+1;
                if(count>1){
                    //查询每个号码出现的频次比
                    int top1=2;
                    int top2=1;
                    int top3=1;
                    if(top1>1){
                        Map<Integer,Integer> compareValue=new HashMap<Integer,Integer>();
                        compareValue.put(top1,count);
                        compareValue.put(top2,count);
                        compareValue.put(top3,count);
                        map.put("compareValue",compareValue);
                    }
                }
                //推送识别和关联的结果
                map.put("regcognition",faceRecognitionInfo);
                String data=JSON.toJSONString(map);



            }
            //抓拍入库





//            String accept = msg.substring(msg.indexOf("{"));

//            byte[] imgBytes = ImageUtil.decodeBase64(accept);
//            try {
//                weedFSService.init();
//            } catch (Exception e) {
//                log.info("图片服务器初始化失败", e);
//            }
//            InputStream fileImputStream = new ByteArrayInputStream(imgBytes);
//            BizResult<String> weedfsStoreResult = weedFSService.storagePic(fileImputStream);
//            if (!weedfsStoreResult.getFlag()){
//                log.info("KafkaReceiverService.class, 图片数据存入失败", weedfsStoreResult.getDesc());
//            }
        }
    }
}
