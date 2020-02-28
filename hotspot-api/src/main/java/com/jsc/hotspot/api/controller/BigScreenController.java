package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.CameraCatInfoService;
import com.jsc.hotspot.api.service.DeviceService;
import com.jsc.hotspot.api.service.HoTnumInfoService;
import com.jsc.hotspot.api.vo.BigScreeenDataVO;
import com.jsc.hotspot.common.bean.Response;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import com.jsc.hotspot.db.entity.Result;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huixing
 * @description 大屏控制层
 * @date 2020/2/21
 */
@RestController
@RequestMapping("/api")
public class BigScreenController {

    private Logger logger = LoggerFactory.getLogger(BigScreenController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private HoTnumInfoService hoTnumInfoService;
    @Autowired
    private CameraCatInfoService cameraCatInfoService;

    /**
     * 大屏读取捕获信息
     * @return
     */
    @RequestMapping(value = "/info/capture", method = RequestMethod.GET)
    public Object captureInfo(){

        try {
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            ValueOperations valueOperations = redisTemplate.opsForValue();
            String allTotal = (String) valueOperations.get("allTotal");
            String todayNumber = (String) valueOperations.get("todayNumber");
            String bidNum = (String) valueOperations.get("bidNum");
            String todayBidNum = (String) valueOperations.get("todayBidNum");
            BigScreeenDataVO bigScreeenDataVO = new BigScreeenDataVO();
            bigScreeenDataVO.setAllTotal(allTotal);
            bigScreeenDataVO.setBidNum(bidNum);
            bigScreeenDataVO.setTodayBidNum(todayBidNum);
            bigScreeenDataVO.setTodayNumber(todayNumber);
            return ResponseUtil.ok(bigScreeenDataVO);
        }catch (Exception e)
        {
            logger.error(e.getMessage());
            return ResponseUtil.ok(new Result(false, "获取失败"));
        }
    }

    /**
     * 大屏读取捕获信息
     * @return
     */
    @GetMapping(value = "/device/status")
    public Object deviceStatusInfo(){
        try {
            List<HotFrontDevice> deviceList = deviceService.getDeviceList();
            return ResponseUtil.ok(deviceList);
        }catch (Exception e)
        {
            logger.error(e.getMessage());
            return ResponseUtil.ok(new Result(false, "获取失败"));
        }
    }

    /**
     * 大屏数据获取
     * @return
     */
    @GetMapping(value = "/data")
    public Object imsiMonthData(){
        try {
            Map<String, Integer> faceMapByDay = cameraCatInfoService.getFaceByDay();
            Map<String, Integer> faceMapByMonth = cameraCatInfoService.getFaceByMonth();
            Map<String, Integer> hotMapByDay = hoTnumInfoService.getNumInfoByDay();
            Map<String, Integer> hotMapByMonth = hoTnumInfoService.getNumInfoByMonth();
            Map<String, Map<String, Integer>> map = new HashMap<>();
            map.put("faceMapByDay", faceMapByDay);
            map.put("faceMapByMonth", faceMapByMonth);
            map.put("hotMapByDay", hotMapByDay);
            map.put("hotMapByMonth", hotMapByMonth);
            return ResponseUtil.ok(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseUtil.ok(new Result(false, "获取失败"));
        }
    }

    @GetMapping(value = "/nums")
    public Object imsiAndFaceNums(){
        try {
            long cameraNums = cameraCatInfoService.countCameraCatNums();
            long currentCameraNums = cameraCatInfoService.currentCameraNums();
            long imsiCatNums = hoTnumInfoService.countImsiCatNums();
            long currentImsiNums = hoTnumInfoService.currentImsiNums();
            Map<String, Long> numMaps = new HashMap<>();
            numMaps.put("cameraNums", cameraNums);
            numMaps.put("currentCameraNums", currentCameraNums);
            numMaps.put("imsiCatNums", imsiCatNums);
            numMaps.put("currentImsiNums", currentImsiNums);
            return ResponseUtil.ok(numMaps);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseUtil.ok(new Result(false, "获取失败"));
        }
    }
}
