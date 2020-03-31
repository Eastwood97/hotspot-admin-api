package com.jsc.hotspot.api;

//import com.jsc.hotspot.api.utils.ExcelUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author huixing
 * @description
 * @date 2020/3/24
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration // 开启web应用配置
@SpringBootTest
@MapperScan({"com.jsc.hotspot.db.dao", "com.jsc.hotspot.db.dao.ext"})
@ComponentScan(basePackages = {"com.jsc.hotspot.db", "com.jsc.hotspot.common", "com.jsc.hotspot.api"})
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@EnableAsync
public class DemoApplicationTests {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
    }

    @Test
    public void hello() throws Exception {
        String[] titles = new String[3];
        titles[0] = "aa";
        titles[1] = "bb";
        titles[2] = "cc";
        String[][] values = new String[3][3];
        values[2][2] = "a";
        values[2][1] = "a";
        values[2][0] = "a";
        values[1][1] = "b";
        values[1][0] = "c";
        values[0][0] = "c";
//        ExcelUtil.getSXSSFWorkbook("aa", titles, values, null);
//        mvc.perform(
//                MockMvcRequestBuilders
//                        .get("/helloword")
//                        .accept(MediaType.APPLICATION_JSON_UTF8)
//        )
//                .andExpect(status().isOk()) // 用于判断返回的期望值
//                .andExpect(content().string(equalTo("Hello World!!!")));

    }
}
