package com.jsc.hotspot.accept;

import com.jsc.hotspot.accept.facade.WeedFSService;
import com.jsc.hotspot.common.biz.BizResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class HotspotAcceptApplicationTests {

    @Autowired
    private WeedFSService weedFSService;

    @Test
    void contextLoads() {
        try {
            InputStream inputStream = new FileInputStream("D:\\menu");
            BizResult<String> bizResult = weedFSService.storagePic(inputStream);
            System.out.println(bizResult.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
