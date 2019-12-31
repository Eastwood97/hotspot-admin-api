package com.jsc.hotspot.api.utils;

import com.jsc.hotspot.api.config.UrlConst;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * @author huixing
 * @description Http工具类封装
 * @date 2019/12/27
 */
public class HttpUtil {

    /**
     * 发送POST请求
     */
    public static String sendPost(String paramJSON, String url) {
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        PostMethod postMethod = new PostMethod(UrlConst.SDK_URL);
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");

        try {
            RequestEntity entity = new StringRequestEntity(paramJSON, "application/json", "UTF-8");
            postMethod.setRequestEntity(entity);
            client.executeMethod(postMethod);
            String result = postMethod.getResponseBodyAsString();
            postMethod.releaseConnection();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
