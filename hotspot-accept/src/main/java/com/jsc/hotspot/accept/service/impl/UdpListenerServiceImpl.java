package com.jsc.hotspot.accept.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.accept.config.WebSocket;
import com.jsc.hotspot.accept.service.*;
import com.jsc.hotspot.db.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalDateTime;
/**
 *
 * 功能描述: Udp
 *
 * @param:
 * @return:
 * @auther: ww
 * @date: 2020/2/27 0027 10:16
 */
@WebListener
public class UdpListenerServiceImpl implements ServletContextListener {
    //创建websocket
    private WebSocket websocket = new WebSocket();
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
    //Udp初始化
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("init udp");
        try {
            new Thread(new UppProcess(UDP_PORT)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Udp删除
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
        public Process(DatagramPacket packet) {
            byte[] buffer = packet.getData();
            System.out.println(System.currentTimeMillis());
            String str = bytesToHex(buffer);
            System.out.println(System.currentTimeMillis());
            String[] split = str.split(" ");
            String imsi = "";
            String imei = "";
            Long devId;
            if (split[0].equals("04")) {
                String substring = split[6].substring(1);
                devId = (long) Integer.parseInt(substring);
                for (int i = 8; i < 23; i++) {
                    imsi = imsi + split[i].substring(1);
                }
                for (int i = 23; i < 38; i++) {
                    imei = imei + split[i].substring(1);
                }
                HotNumInfo hotNumInfo = new HotNumInfo();
                if ((imsi.indexOf("46011")) == -1 && (imsi.indexOf("46003")) == -1) {
                    if (imsi.indexOf("460") != -1) {
                        String num = GetPhoneNumByIMSI(imsi);
                        if ("".equals(num)) {
                            hotNumInfo.setAttribution("中国");
                        } else {
                            NumArea area = numAreaService.selectNumArea(num);
                            if (area != null) {
                                hotNumInfo.setAttribution("中国" + area.getProvince() + area.getCity());
                            } else {
                                hotNumInfo.setAttribution("中国");
                            }
                        }
                    } else {
                        int i = Integer.parseInt(imsi.substring(0, 3));
                        String areaString = imsiAreaService.selectImsiArea(i);
                        if ("".equals(areaString)) {
                            hotNumInfo.setAttribution("");
                        } else {
                            hotNumInfo.setAttribution(areaString);
                        }
                    }
                } else {
                    hotNumInfo.setAttribution("");
                }
                //此处在校验黑名单信息
                HotTargetInfo List = hotTargetInfoService.selectHotTargetInfoList(imsi, imei);
                if (List != null) {
                    HotCompareResult hotCompareResult = new HotCompareResult();
                    hotCompareResult.setFrtDevId(devId);
                    hotCompareResult.setImei(imei);
                    hotCompareResult.setImsi(imsi);
                    hotCompareResult.setIsdn("");
                    hotCompareResult.setCaptureTime(LocalDateTime.now());
                    hotCompareResult.setTargetId(List.getTargetId());
                    LocalDateTime localDateTime = LocalDateTime.now();
                    hotCompareResult.setCaptureTime(localDateTime);
                    hotCompareResult.setCreateTime(LocalDateTime.now());
                    hotCompareResult.setUpdateTime((LocalDateTime.now()));
                    hotCompareResultService.insertHotCompareResult(hotCompareResult);
                }
                hotNumInfo.setDevId(devId);
                hotNumInfo.setImsi(imsi);
                hotNumInfo.setImei(imei);
                hotNumInfo.setIsdn(hotNumInfo.getIsdn());
                hotNumInfo.setTargetId(hotNumInfo.getTargetId());
                hotNumInfo.setCaptureTime(LocalDateTime.now());
                hotNumInfo.setCreateTime(LocalDateTime.now());
                hotNumInfo.setUpdateTime((LocalDateTime.now()));
                HoTnumInfoService.insertHoTnumInfoNum(hotNumInfo);
                //websocket推送实时取号信息
                String hotNumInfoJson = JSONObject.toJSONString(hotNumInfo);
                websocket.sendAllMessage(hotNumInfoJson);
            }
        }


        @Override
        public void run() {
        }
    }



    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 40; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex).append(" ");
        }
        return sb.toString();
    }
    public String GetPhoneNumByIMSI(String imsi) {
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
            return num;
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
                return "";//"不支持该类型IMSI码查询"
            }
            num += mobileid.substring(3, 4) + mobileid.substring(0, 3);
            return num;
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
                return "";//不支持该类型IMSI码查询
            }
            num += mobileid.substring(1, 5);
            return num;
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
                return "";//不支持该类型IMSI码查询
            }
            num += mobileid.substring(1, 5);
            return num;
        } else {
            return "";//运营商代码不支持
        }
    }
}