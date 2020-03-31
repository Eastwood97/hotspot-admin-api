package com.jsc.hotspot.api.controller;

//import com.jsc.hotspot.api.utils.ExcelUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huixing
 * @description
 * @date 2020/3/24
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping(value = "ttt", method = RequestMethod.GET)
    public SXSSFWorkbook aa(){
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
        return null;
    }
}
