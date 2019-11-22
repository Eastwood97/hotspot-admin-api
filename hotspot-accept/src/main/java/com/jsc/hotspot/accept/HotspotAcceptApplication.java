package com.jsc.hotspot.accept;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;

@EnableFeignClients
@MapperScan("com.jsc.hotspot.db.dao")
@SpringBootApplication(exclude = {RedissonAutoConfiguration.class})
@ComponentScan(basePackages = {"com.jsc.hotspot.db", "com.jsc.hotspot.common", "com.jsc.hotspot.accept"})
@ServletComponentScan
@Slf4j
public class HotspotAcceptApplication {

    //TODO 录制完视频后保存到文件系统中，并将文件系统的ID保存到数据库中，后期通过时间来查
    public String streamURL = "http://192.201.102.100/hls/cctv.m3u8";//流地址 网上有自行百度
    public String filePath;//文件路径
    public Integer id;//案件id
    public Integer audioChannel;//是否录制声音


    public static void main(String[] args) {
        SpringApplication.run(HotspotAcceptApplication.class, args);
    }

    public void run(ApplicationArguments args) throws Exception {
        // 获取视频源 可以获取RTSP视频源进行视频播放
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(streamURL);
        FFmpegFrameRecorder recorder = null;
        System.out.println("RUN THREAD");
        try {
            grabber.start();
            Frame frame = grabber.grabFrame();
            if (frame != null) {
                //保存到本地的文件
                File outFile=new File("/Users/huixing/test.mp4");
                if(!outFile.isFile()) outFile.createNewFile();
                // 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
                recorder = new FFmpegFrameRecorder("/Users/huixing/test.mp4", frame.imageWidth, frame.imageHeight, 1);
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);//直播流格式
                recorder.setFormat("mp4");//录制的视频格式
                recorder.setFrameRate(25);//帧数
                recorder.start();
                while ((frame != null)) {
                    recorder.record(frame);//录制
                    frame = grabber.grabFrame();//获取下一帧
                }
                recorder.record(frame);
                //停止录制
                recorder.stop();
                grabber.stop();
            }
        } catch (FrameGrabber.Exception e) {
            log.error("视频录制异常", e);
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            log.error("视频录制异常", e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != grabber) {
                try {
                    grabber.stop();
                } catch (FrameGrabber.Exception e) {
                    log.error("视频录制异常", e);
                    e.printStackTrace();
                }
            }
            if (recorder != null) {
                try {
                    recorder.stop();
                } catch (FrameRecorder.Exception e) {
                    log.error("视频录制异常", e);
                    e.printStackTrace();
                }
            }
        }
    }
}
