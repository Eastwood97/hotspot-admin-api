package com.jsc.hotspot.api;

import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.api.service.TargetFaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// AppTest.java file
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotspotApiApplication.class)
public class HotspotApiApplicationTests {

    @Autowired
    private TargetFaceService targetFaceService;

    @Test
    public void test1() {

        TargetFace targetFace = new TargetFace();
        targetFace.setFileId1("1,2232dsd");
        targetFace.setFileId2("2,dxzsda");
        targetFace.setFileId3("3,3dscz");
        targetFace.setTargetName("2323");
        targetFace.setDesc("scZxzczxc");
        targetFaceService.add(targetFace);
    }

}
