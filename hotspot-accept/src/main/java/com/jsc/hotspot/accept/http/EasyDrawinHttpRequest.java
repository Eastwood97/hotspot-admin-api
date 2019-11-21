package com.jsc.hotspot.accept.http;

import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.accept.consts.EasyDrawinUrl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by huixing on 2019/11/17.
 */
//@Service
//@FeignClient(url = "http://192.168.95.129:10008",name="easydrawin")
public interface EasyDrawinHttpRequest {

    @RequestMapping(value= "/api/v1/stream/start",method= RequestMethod.GET)
    String startStream(@RequestParam("url") String url);

    @RequestMapping(value= "/api/v1/stream/stop",method= RequestMethod.GET)
    JSONObject stopStream (@RequestParam("url") String id);


}
