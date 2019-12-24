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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableFeignClients
@MapperScan({"com.jsc.hotspot.db.dao","com.jsc.hotspot.accept.Mapper"})
@SpringBootApplication(exclude = {RedissonAutoConfiguration.class})
@ComponentScan(basePackages = {"com.jsc.hotspot.db", "com.jsc.hotspot.common", "com.jsc.hotspot.accept"})
@ServletComponentScan
@Slf4j
public class HotspotAcceptApplication {

    //TODO 录制完视频后保存到文件系统中，并将文件系统的ID保存到数据库中，后期通过时间来查
    public String streamURL = "http://192.201.102.100/hls/cctv.m3u8";//流地址
    public String filePath;//文件路径
    public Integer id;//案件id
    public Integer audioChannel;//是否录制声音


    public static void main(String[] args) {
        SpringApplication.run(HotspotAcceptApplication.class, args);
    }
}
