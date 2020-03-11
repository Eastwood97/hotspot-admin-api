package com.jsc.hotspot.api.facade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.api.config.WebSocket;
import com.jsc.hotspot.api.dto.FaceRecognitionInfoDTO;
import com.jsc.hotspot.api.facade.KafkaReceiverService;
import com.jsc.hotspot.api.vo.ImsiRatio;
import com.jsc.hotspot.db.dao.*;
import com.jsc.hotspot.db.dao.ext.RelatedNumEXTMapper;
import com.jsc.hotspot.db.domain.*;
import com.jsc.hotspot.db.po.RealatedNumAndCount;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author tzm
 * @description kafka接收服务实现
 * @date 2019/11/4
 */
@Service
public class KafkaReceiverServiceImpl implements KafkaReceiverService {

    private static Log log = LogFactory.getLog(KafkaReceiverServiceImpl.class);


    @Autowired
    private static HotNumInfoMapper hotNumInfoMapper;


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


    ExecutorService es = Executors.newFixedThreadPool(10);

    /**
     * 监听 "picTopic" 将图片数据存入文件系统和数据库
     *
     * @param record
     */
    @Async
    @KafkaListener(topics = {"picTopic"})
    public void listen(ConsumerRecord<?, ?> record) throws InvocationTargetException, IllegalAccessException {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        log.error("-----===========---------+++++++++++++++++++");
        if (kafkaMessage.isPresent()) {
            RelatedNumResultExample relatedNumResultExample = new RelatedNumResultExample();
            CameraCompareResultExample cameraCompareResultExample = new CameraCompareResultExample();
            HotNumInfoExample hotNumInfoExample = new HotNumInfoExample();
            FaceRecognitionInfoDTO faceRecognitionInfo = new FaceRecognitionInfoDTO();
            Object message = kafkaMessage.get();
            String msg = (String) message;//json字符串
            JSONObject jsonObject = JSON.parseObject(msg);//json对象

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String stringTime = jsonObject.getString("captureTime");
            LocalDateTime captureTime = LocalDateTime.parse(stringTime, df);
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
            if (faceRecognitionInfo.getCompareScore() > 0.6) {
                cameraCatInfo.setQuality(1); //抓拍质量设为1，表示为目标
                //给抓拍表中关联上目标的名称
                cameraCatInfo.setTargetName(faceRecognitionInfo.getTargetName());
                CameraCompareResult result = campareSuccess(faceRecognitionInfo);
                log.error("-------------------------比对成功结果与关联号码入库-------------");

                //做关联性处理
                CameraCompareResultExample.Criteria criteriaOfResult = cameraCompareResultExample.createCriteria();
                criteriaOfResult.andTargetNameEqualTo(faceRecognitionInfo.getTargetName());
                //查出该目标中标的次数
                int count = (int) (cameraCompareResultMapper.countByExample(cameraCompareResultExample));
                Map<String, Object> jsonMap = correlationProcess(faceRecognitionInfo, count);
                String stringMap = JSON.toJSONString(jsonMap);
                //json 字符串转object
                JSONObject relatedResult = JSON.parseObject(stringMap);
                map.put("realatedNumAndCount", relatedResult);
                //实例化一个对象承载关联结果
                RelatedNumResult relatedNumResult = new RelatedNumResult();
                relatedNumResult.setCaptureNum(count);
                //更新关联结果
                relatedNumResult.setRelatedResult(relatedResult);
                relatedNumResultExample.or().andTargetNameEqualTo(result.getTargetName());
                List<RelatedNumResult> list = relatedNumResultMapper.selectByExampleSelective(relatedNumResultExample);
                if (list.size() > 0) {
                    RelatedNumResultExample.Criteria criteria = relatedNumResultExample.createCriteria();
                    criteria.andTargetNameEqualTo(result.getTargetName());
                    log.error("更新关联结果表前");
                    relatedNumResultMapper.updateByExampleSelective(relatedNumResult, relatedNumResultExample);
                    log.error("更新关联结果表后");
                } else {
                    relatedNumResult.setTargetName(result.getTargetName());
                    relatedNumResult.setTargetFace(result.getTargetFaceStorageUrl());
                    log.error("插入relatedNum表");
                    relatedNumResultMapper.insertSelective(relatedNumResult);
                    log.error("插入relatedNum表结束");
                }

            }
            String data = JSON.toJSONString(map);
            webSocket.sendOneMessage("admin123", data);
            webSocket.sendOneMessage("bigData", data);

            BeanUtils.copyProperties(cameraCatInfo, faceRecognitionInfo);
            cameraCatInfo.setCreateTime(LocalDateTime.now());
            //抓拍入库
            cameraCatInfoMapper.insertSelective(cameraCatInfo);
        }
    }


    /**
     * 冒泡排序,优化版
     *
     * @param array
     */

    private void sort(ArrayList<Double> array) {
        Double tmp = 0.0;
        for (int i = 0; i < array.size(); i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < array.size() - i - 1; j++) {
                if (array.get(j) < array.get(j + 1)) {
                    tmp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, tmp);
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 人脸比对入库
     *
     * @param faceRecognitionInfo
     * @return
     */

    private CameraCompareResult campareSuccess(FaceRecognitionInfoDTO faceRecognitionInfo) {

        //人脸识别成功的数据载体
        CameraCompareResult result = new CameraCompareResult();
        try {
            BeanUtils.copyProperties(result, faceRecognitionInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        result.setCreateTime(LocalDateTime.now());

        //2.添加人脸中标结果到数据库
        cameraCompareResultMapper.insertSelective(result);

        //取关联号码 并且入库
        findRelatedNum(result.getCaptureTime(), faceRecognitionInfo);
        return result;
    }

    /**
     * 关联号码入库
     *
     * @param captureTime
     * @param faceRecognitionInfo
     */
    private void findRelatedNum(LocalDateTime captureTime, FaceRecognitionInfoDTO faceRecognitionInfo) {
        // 3.根据中标时间查询取号
        Callable<List> numberListCallable = new Callable<List>() {
            @Override
            public List call() throws Exception {
                java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(), captureTime);
                long sleepTime = 10000 - duration.toMillis();
                Thread.sleep(sleepTime);
                log.error("------------------------------醒来-----------------------------------------");
                LocalDateTime beforDate = captureTime.minusSeconds(30);
                LocalDateTime afterDate = captureTime.plusSeconds(30);
                HotNumInfoExample example = new HotNumInfoExample();
                example.or().andCaptureTimeBetween(beforDate, afterDate);
                log.error("############前后30秒查询");
                return hotNumInfoMapper.selectByExampleSelective(example);
            }
        };

        FutureTask<List> numberListTask = new FutureTask<>(numberListCallable);
        es.submit(numberListTask);
        List<HotNumInfo> numberList = null;
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
        if (null != numberList || numberList.size() > 0) {
            log.error("入库前先去重");
            //使用TreeSet去重
            List<HotNumInfo> unique = numberList.stream().collect(
                    collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getImsi()))),
                            ArrayList::new));
            log.error("插入relatedNum表");
            for (HotNumInfo number : unique
            ) {
                relatedNum.setImei(number.getImei());
                relatedNum.setImsi(number.getImsi());
                relatedNum.setIsdn(number.getIsdn());
                relatedNum.setTargetName(faceRecognitionInfo.getTargetName());
                relatedNum.setNumberId(number.getId());
                relatedNumMapper.insertSelective(relatedNum);
            }
            log.error("插入relatedNum表结束");
        }
    }

    private Map<String, Object> correlationProcess(FaceRecognitionInfoDTO faceRecognitionInfo, int count) {


        //查出一些高频的关联号码
        List<RealatedNumAndCount> realatedNumAndCounts = relatedNumEXTMapper.getCampareValue(faceRecognitionInfo.getTargetName());
        //存放最终的返回结果
        Map<String, Object> jsonMap = new HashMap<>();
        if (null != realatedNumAndCounts && realatedNumAndCounts.size() > 0) {
            //两次卷积去除干扰
            List<ImsiRatio> imsiRatioList = new ArrayList<>();
            ArrayList<Double> arrayList = new ArrayList<>();
            for (int i = 0; i < realatedNumAndCounts.size(); i++) {
                ImsiRatio imsiRatio = new ImsiRatio();
                String imsi = realatedNumAndCounts.get(i).getImsi();
                HotNumInfoExample example = new HotNumInfoExample();
                example.or().andImsiEqualTo(imsi);
                long totalCount = hotNumInfoMapper.countByExample(example);
                double relatedCount = realatedNumAndCounts.get(i).getCount();
                //算出关联次数比上号次数 ，常住人口关联号码更稀疏，可以去除常驻人口干扰
                Double ratio = relatedCount / totalCount;
                imsiRatio.setImsi(imsi);
                imsiRatio.setRatio(ratio);
                imsiRatio.setRelatedCount(relatedCount);
                imsiRatioList.add(imsiRatio);
                arrayList.add(ratio);
            }
            //按比值降序排列
            sort(arrayList);
            //用于第二次卷积的数据容器
            List<ImsiRatio> ratioList = new ArrayList<>();
            //存放第二次比值，用于排序和去除干扰
            ArrayList<Double> arrayList2 = new ArrayList<>();
            //取前半部分加一
            for (int i = 0; i < (arrayList.size() / 2) + 2; i++) {
                for (int j = 0; j < imsiRatioList.size(); j++) {
                    ImsiRatio imsiRatio = imsiRatioList.get(j);
                    if (imsiRatio.getRatio() == arrayList.get(i)) {
                        ratioList.add(imsiRatio);
                        double ratioTwo = imsiRatio.getRelatedCount() / count;
                        imsiRatio.setRatioTwo(ratioTwo);
                        arrayList2.add(ratioTwo);
                        break;
                    }
                }
            }
            //降序排列
            sort(arrayList2);
            String[] rank = {"topOne", "topTwo", "topThree"};
            for (int i = 0; i < (arrayList2.size() / 2) + 1; i++) {
                for (int j = 0; j < ratioList.size(); j++) {
                    ImsiRatio imsiRatio = ratioList.get(j);
                    if (imsiRatio.getRatioTwo() == arrayList2.get(i)) {
                        jsonMap.put(rank[i], imsiRatio);
                        break;
                    }
                }

            }
        }
        return jsonMap;
    }


//    /**
//     * 占比计算保留小数的位数方法
//     * 转成百分数
//     * 当前数除以总数
//     *
//     * @param num1 ,num2  num1/num2
//     * @return rate  保留2位小数的
//     */
//    public static String division(int num1, int num2) {
//        String rate = "0.00%";
//        //定义格式化起始位数
//        String format = "0.00";
//        if (num2 != 0 && num1 != 0) {
//            DecimalFormat dec = new DecimalFormat(format);
//            rate = dec.format((double) num1 / num2 * 100) + "%";
//            while (true) {
//                if (rate.equals(format + "%")) {
//                    format = format + "0";
//                    DecimalFormat dec1 = new DecimalFormat(format);
//                    rate = dec1.format((double) num1 / num2 * 100) + "%";
//                } else {
//                    break;
//                }
//            }
//        } else if (num1 != 0 && num2 == 0) {
//            rate = "100%";
//        }
//        return rate;
//    }
//
//
//    /**
//     * 把上面得到的百分比转为字符串类型的小数  保留两位小数
//     *
//     * @author shw
//     */
//    public static BigDecimal perToDecimal(String percent) {
//        String decimal = percent.substring(0, percent.indexOf("%"));
//        BigDecimal bigDecimal = new BigDecimal(decimal);
//        bigDecimal.divide(new BigDecimal("100"), 4, BigDecimal.ROUND_HALF_UP);
//        return bigDecimal;
//    }
}

