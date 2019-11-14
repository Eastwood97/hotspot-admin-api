package com.jsc.hotspot.accept.facade.impl;

import com.jsc.hotspot.accept.facade.KafkaSender;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huixing
 * @description 服务端处理
 * @date 2019/10/30
 */
@Component
@Qualifier("serverHandler")
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    private ConcurrentHashMap<String, String> cameraData = new ConcurrentHashMap<>();

    @Autowired
    private KafkaSender kafkaSender;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) {
        String clientIdToShort= ctx.channel().id().asShortText();
        if (cameraData.containsKey(clientIdToShort)){
            cameraData.replace(clientIdToShort, cameraData.get(clientIdToShort) + msg);
        }else {
            cameraData.put(clientIdToShort, msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

        ctx.channel().writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("\nChannel is disconnected");
        //kafkaSender.send("msg: " + msg);
        String data = cameraData.get(ctx.channel().id().asShortText());
//        log.info(data);
        kafkaSender.send(data);
        // 放入Kafka
        System.out.println(cameraData.get(ctx.channel().id().asShortText()));
        super.channelInactive(ctx);
    }
}
