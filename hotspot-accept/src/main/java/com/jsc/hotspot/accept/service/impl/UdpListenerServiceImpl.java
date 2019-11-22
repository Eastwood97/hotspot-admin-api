package com.jsc.hotspot.accept.service.impl;

import com.jsc.hotspot.accept.service.HoTnumInfoService;
import com.jsc.hotspot.accept.service.HotCompareResultService;
import com.jsc.hotspot.accept.service.HotTargetInfoService;
import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.List;
@WebListener
public class UdpListenerServiceImpl implements ServletContextListener {
    @Autowired
    private HoTnumInfoService HoTnumInfoService;
    @Autowired
    private HotTargetInfoService hotTargetInfoService;
    @Autowired
    private HotCompareResultService hotCompareResultService;
    public static final int MAX_UDP_DATA_SIZE = 4096;
    public static final int UDP_PORT = 9900;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init udp");
        try {
            new Thread(new UppProcess(UDP_PORT)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("udp Destroyed");
    }

    class UppProcess implements Runnable {
        DatagramSocket datagramSocket = null;

        public UppProcess(final int port) throws SocketException {
            datagramSocket = new DatagramSocket(port);
        }

        @Override
        public void run() {
            System.err.println("start udp");
            while (true) {
                byte[] buffer = new byte[MAX_UDP_DATA_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    datagramSocket.receive(packet);
                    new Thread(new Process(packet)).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Process implements Runnable {
        public Process(DatagramPacket packet) throws Exception {
            byte[] buffer = packet.getData();
            String str = new String(buffer, "utf-8").trim();
            String[] split = str.split(" ");
            String imsi = "";
            String imei = "";
            if (split[0].equals("04")) {
                Integer devId = Integer.parseInt(split[6].substring(1));
                for (int i = 8; i < 23; i++) {
                    imsi = imsi + split[i].substring(1);
                }
                for (int i = 23; i < 38; i++) {
                    imei = imei + split[i].substring(1);
                }
                System.err.println(imsi);
                HotNumInfo hotNumInfo = new HotNumInfo();
//            hotNumInfo.setDevId(devId);
//            hotNumInfo.setImsi();
//            hotNumInfo.setImei();
//            hotNumInfo.setMode();
//            hotNumInfo.setIsdn();
//            hotNumInfo.setTargetId();
//            hotNumInfo.setAttribution(getAreaString(imsi));
                HoTnumInfoService.insertHoTnumInfoNum(hotNumInfo);
                List<HotTargetInfo> List = hotTargetInfoService.selectHotTargetInfoList(imsi, imei);
                if (List != null) {
                    HotCompareResult hotCompareResult = new HotCompareResult();
//                hotCompareResult.setFrtDevId();
//                hotCompareResult.setImei();
//                hotCompareResult.setImsi();
//                hotCompareResult.setIsdn();
//                hotCompareResult.setTargetId();
//                hotCompareResult.setMode();
                    hotCompareResultService.insertHotCompareResult(hotCompareResult);
                }
            }

        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
        }
    }
}