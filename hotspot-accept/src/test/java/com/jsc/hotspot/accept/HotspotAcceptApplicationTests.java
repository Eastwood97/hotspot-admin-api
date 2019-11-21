package com.jsc.hotspot.accept;

import com.alibaba.fastjson.JSON;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = WebServiceApplication.class)
public class HotspotAcceptApplicationTests {


//    @Autowired
//    private RestTemplate restTemplate;


    @Test
    public void contextLoads() throws InterruptedException, FrameGrabber.Exception {
//        restTemplate.getForObject("ss");

        // 请求获取文件夹
        LocalDateTime startTime = LocalDateTime.now();

        Thread.sleep(3000);

        LocalDateTime endTime = LocalDateTime.now();

        Duration duration = Duration.between(endTime, startTime);

        System.out.println(duration.toMillis());
//        String notice = restTemplate.getForObject("http://192.201.102.100:10008/api/v1/record/folders"
//                , String.class);
//
//        Result result = JSON.parseObject(notice, Result.class);
//        System.out.println(notice);

        // 请求获取文件
//        Map<String,String> map = new HashMap();
//        map.put("folder","stream_265");
//        String notice = restTemplate.getForObject("http://192.201.102.100:10008/api/v1/record/files?folder=stream_265"
//                , String.class);
//        System.out.println(notice);
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//        grabber.start();   //开始获取摄像头数据
//        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
//        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        canvas.setAlwaysOnTop(true);
//
//        while(true)
//        {
//            if(!canvas.isDisplayable())
//            {//窗口是否关闭
//                grabber.stop();//停止抓取
//                System.exit(2);//退出
//            }
//            canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
//
//            Thread.sleep(50);//50毫秒刷新一次图像
//        }
    }

}

class Result{

    Integer total;
    List<rows> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<com.jsc.hotspot.accept.rows> getRows() {
        return rows;
    }

    public void setRows(List<com.jsc.hotspot.accept.rows> rows) {
        this.rows = rows;
    }
}

class rows{
    String folder;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}