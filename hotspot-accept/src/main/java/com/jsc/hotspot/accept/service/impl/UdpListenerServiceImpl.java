package com.jsc.hotspot.accept.service.impl;


import com.jsc.hotspot.accept.service.*;
import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@WebListener
public class UdpListenerServiceImpl implements ServletContextListener {
    private HashMap<Integer, String> dicorg = new HashMap<Integer, String>();
    @Autowired
    private HoTnumInfoService HoTnumInfoService;
    @Autowired
    private HotTargetInfoService hotTargetInfoService;
    @Autowired
    private HotCompareResultService hotCompareResultService;
    @Autowired
    private NumAreaService numAreaService;
    @Autowired
    private ImsiAreaService imsiAreaService;

    public static final int MAX_UDP_DATA_SIZE = 4096;
    public static final int UDP_PORT = 9999;

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
                hotNumInfo.setDevId((long) 1);
                imsi = "460001084591004";
                hotNumInfo.setImsi("460001084591004");
                hotNumInfo.setImei(imei);
//            hotNumInfo.setMode();
//            hotNumInfo.setIsdn();
//            hotNumInfo.setTargetId();
                System.out.println(imsi.indexOf("46011"));
                System.out.println(imsi.indexOf("46003"));
                hotNumInfo.setCaptureTime(LocalDateTime.now());
                if ((imsi.indexOf("46011")) == -1 && (imsi.indexOf("46003")) == -1) {
                    if(imsi.indexOf("460")!=-1){
                        String areaString = getAreaString("460001084591004");
                        System.out.println(areaString);
                        hotNumInfo.setAttribution(areaString);
                    }else {
                        int i = Integer.parseInt("234108086242750".substring(0, 3));
                        String areaString = imsiAreaService.selectImsiArea(i);
                        System.out.println(areaString);
                        hotNumInfo.setAttribution(areaString);
                    }
                }
                HoTnumInfoService.insertHoTnumInfoNum(hotNumInfo);
//                List<HotTargetInfo> List = hotTargetInfoService.selectHotTargetInfoList(imsi, imei);
//                if (List != null) {
//                    HotCompareResult hotCompareResult = new HotCompareResult();
////                hotCompareResult.setFrtDevId(1);
////                hotCompareResult.setImei(1);
////                hotCompareResult.setImsi(1);
////                hotCompareResult.setIsdn();
////                hotCompareResult.setTargetId();
////                hotCompareResult.setMode();
//                    hotCompareResultService.insertHotCompareResult(hotCompareResult);
//                }
            }
        }
        @Override
        public void run() {
        }
    }

    public String getAreaString(String imsi) {
        StringBuffer numSB = new StringBuffer();
        GetPhoneNumByIMSI(imsi, numSB);
        String num = numSB.toString();
        return numAreaService.selectNumArea(Integer.parseInt(num));
    }

    public int GetPhoneNumByIMSI(String imsi, StringBuffer numSB) {
        String num = "";
        String networkid = imsi.substring(3, 5);
        String mobileid = imsi.substring(5, 10);
        if (networkid.equals("00"))//移动
        {
            String idflag = mobileid.substring(3, 4);
            if (idflag.equals("5")) {
                num = "1350";
            } else if (idflag.equals("6")) {
                num = "1360";
            } else if (idflag.equals("7")) {
                num = "1370";
            } else if (idflag.equals("8")) {
                num = "1380";
            } else if (idflag.equals("9")) {
                num = "1390";
            } else if (idflag.equals("0")) {
                num = "135" + mobileid.substring(4, 5);
            } else if (idflag.equals("1")) {
                num = "136" + mobileid.substring(4, 5);
            } else if (idflag.equals("2")) {
                num = "137" + mobileid.substring(4, 5);
            } else if (idflag.equals("3")) {
                num = "138" + mobileid.substring(4, 5);
            } else if (idflag.equals("4")) {
                num = "139" + mobileid.substring(4, 5);
            }
            num += mobileid.substring(0, 3);
            numSB.append(num);
            return 0;
        } else if (networkid.equals("01"))//联通
        {
            String numid = mobileid.substring(4, 5);
            if (numid.equals("0") || numid.equals("1")) {
                num = "130";
            } else if (numid.equals("9")) {
                num = "131";
            } else if (numid.equals("2")) {
                num = "132";
            } else if (numid.equals("3")) {
                num = "156";
            } else if (numid.equals("4")) {
                num = "155";
            } else if (numid.equals("5")) {
                num = "185";
            } else if (numid.equals("6")) {
                num = "186";
            } else if (numid.equals("7")) {
                num = "145";
            } else {
                return 3;//"不支持该类型IMSI码查询"
            }
            num += mobileid.substring(3, 4) + mobileid.substring(0, 3);
            numSB.append(num);
            return 0;
        } else if (networkid.equals("02"))  //移动
        {
            char numid = mobileid.charAt(0);
            if (numid == '0') {
                num = "134";
            } else if (numid == '1') {
                num = "151";
            } else if (numid == '2') {
                num = "152";
            } else if (numid == '3') {
                num = "150";
            } else if (numid == '7') {
                num = "187";
            } else if (numid == '9') {
                num = "159";
            } else if (numid == '8') {
                num = "158";
            } else {
                return 3;//不支持该类型IMSI码查询
            }
            num += mobileid.substring(1, 5);
            numSB.append(num);
            return 0;
        } else if (networkid.equals("07"))    //157 or 188 存在疑问
        {
            char numid = mobileid.charAt(0);
            if (numid == '7') {
                num = "157";
            } else if (numid == '8') {
                num = "188";
            } else if (numid == '9') {
                num = "147";
            } else {
                return 3;//不支持该类型IMSI码查询
            }
            num += mobileid.substring(1, 5);
            numSB.append(num);
            return 0;
        } else {
            return 2;//运营商代码不支持
        }
    }

}