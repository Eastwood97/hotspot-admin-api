package com.jsc.hotspot.accept;

import com.jsc.hotspot.accept.handler.UdpDecoderHandler;
import com.jsc.hotspot.accept.handler.UdpEncoderHandler;
import com.jsc.hotspot.accept.handler.UdpHandler;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Flux;
import reactor.netty.udp.UdpServer;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableFeignClients
@MapperScan({"com.jsc.hotspot.db.dao","com.jsc.hotspot.accept.Mapper"})
@SpringBootApplication
@ComponentScan(basePackages = {"com.jsc.hotspot.db", "com.jsc.hotspot.common", "com.jsc.hotspot.accept"})
@ServletComponentScan
@Slf4j
@EnableScheduling
public class HotspotAcceptApplication {

    //TODO 录制完视频后保存到文件系统中，并将文件系统的ID保存到数据库中，后期通过时间来查
    public String streamURL = "http://192.201.102.100/hls/cctv.m3u8";//流地址
    public String filePath;//文件路径
    public Integer id;//案件id
    public Integer audioChannel;//是否录制声音

    public static void main(String[] args) {
        SpringApplication.run(HotspotAcceptApplication.class, args);
    }

//    @Bean
//    CommandLineRunner serverRunner(UdpDecoderHandler udpDecoderHanlder, UdpEncoderHandler udpEncoderHandler, UdpHandler udpHandler) {
//        return strings -> {
//            createUdpServer(udpDecoderHanlder, udpEncoderHandler, udpHandler);
//        };
//    }
//
//    /**
//     *
//     * 创建UDP Server
//     * @param udpDecoderHandler： 用于解析UDP Client上报数据的handler
//     * @param udpEncoderHandler： 用于向UDP Client发送数据进行编码的handler
//     * @param udpHandler: 用户维护UDP链接的handler
//     */
//    private void createUdpServer(UdpDecoderHandler udpDecoderHandler, UdpEncoderHandler udpEncoderHandler, UdpHandler udpHandler) {
//        UdpServer.create()
//                .handle((in,out) -> {
//                    in.receive()
//                            .asByteArray()
//                            .subscribe();
//                    return Flux.never();
//                })
//                .port(99789) //UDP Server端口
//                .doOnBound(conn -> conn
//                        .addHandler("decoder",udpDecoderHandler)
//                        .addHandler("encoder", udpEncoderHandler)
//                        .addHandler("handler", udpHandler)
//                ) //可以添加多个handler
//                .bindNow(Duration.ofSeconds(30));
//    }
}
