package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.dto.FileRowsDTO;
import com.jsc.hotspot.api.dto.FilesResultDTO;
import com.jsc.hotspot.api.dto.RecordSearchDTO;
import com.jsc.hotspot.api.facade.RestService;
import com.jsc.hotspot.api.service.DeviceService;
import com.jsc.hotspot.api.service.VideoPlayerService;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huixing
 * @description 视频播放控制层
 * @date 2019/11/10
 */
@RestController
@RequestMapping("/admin/video")
public class VideoPlayerController {

    @Autowired
    private VideoPlayerService videoPlayerService;
    @Autowired
    private RestService restService;
    @Autowired
    private DeviceService deviceService;

    @Value("${record.ip}")
    private String recordIp;

    /**
     * 获取直播视频流
     * @return
     */
    @GetMapping("/live")
    public Object getVideoRTSPUrl(){
        BizResult<List<String>> listBizResult = videoPlayerService.getVideoRTSPUrl();
        if (!listBizResult.getFlag()){
            return ResponseUtil.fail(400, listBizResult.getDesc());
        }else {
            return ResponseUtil.okList(listBizResult.getData());
        }
    }

    /**
     * 按条件获取录播视频流
     * @return
     */
    @GetMapping("/record")
    public Object getRecordVideo(@RequestParam("startTime") LocalDateTime startTime,
                                 @RequestParam("endTime") LocalDateTime endTime,
                                 @RequestParam("devId") String devName){
        //DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        BizResult<FilesResultDTO> filesResultDTOBizResult = restService.getFiles();
        // 1。从设备列表中取出设备更新时间
        HotFrontDevice hotFrontDevice = deviceService.getDeviceByDevName(devName);

        // 2。如果更新时间和开始时间是同一天则 使用（开始时间 - 更新时间）=（ts时间段求和）
        Duration duration = Duration.between(startTime, hotFrontDevice.getUpdateTime());
        Duration duration1 = Duration.between(endTime, hotFrontDevice.getUpdateTime());
        List<FileRowsDTO> files = new ArrayList<>();
        // 开始时间 < 更新时间
        if (duration.toMillis() < 0){
            startTime = hotFrontDevice.getUpdateTime();
            long m3u8millis = 0;
            Duration durationFromStartToEnd = Duration.between(endTime, startTime);
            for (FileRowsDTO fileRowsDTO : filesResultDTOBizResult.getData().getFileRowsDTOList()) {
                m3u8millis = m3u8millis + fileRowsDTO.getDurationMillis();
                if (m3u8millis < durationFromStartToEnd.toMillis())
                    break;
                files.add(fileRowsDTO);
            }
            return ResponseUtil.okList(files);
            // 直接获取m3u8文件全部播放
        } else {
            LocalDate startDate = startTime.toLocalDate();
            LocalDate endDate = endTime.toLocalDate();

            Duration duration2 = Duration.between(startDate, endDate);
            if (duration2.isZero()) {
                // 差值
                long millis = duration.toMillis();
                List<FileRowsDTO> fileRowsDTOList = filesResultDTOBizResult.getData().getFileRowsDTOList();
                long m3u8millis = 0;
                for (FileRowsDTO fileRowsDTO : fileRowsDTOList) {
                    m3u8millis = m3u8millis + fileRowsDTO.getDurationMillis();
                    if (m3u8millis > millis){
                        if (m3u8millis < duration1.toMillis())
                            break;
                        files.add(fileRowsDTO);
                    }
                }
                return ResponseUtil.okList(files);
            }else {

                LocalDateTime zero = LocalDateTime.of(startDate, LocalTime.MIN);
                Duration durationFrom0ToStart = Duration.between(startTime, zero);
                Duration durationFrom0ToEnd = Duration.between(endTime, zero);
                // 差值
                long millis = durationFrom0ToStart.toMillis();
                List<FileRowsDTO> fileRowsDTOList = filesResultDTOBizResult.getData().getFileRowsDTOList();
                long m3u8millis = 0;
                int count = 0;
                for (FileRowsDTO fileRowsDTO : fileRowsDTOList) {
                    m3u8millis = m3u8millis + fileRowsDTO.getDurationMillis();
                    if (m3u8millis > millis){
                        if (m3u8millis < durationFrom0ToEnd.toMillis())
                            break;
                        files.add(fileRowsDTO);
                        count++;
                    }
                }
                return ResponseUtil.okList(files);
            }
        }

    }
}
