package com.jsc.hotspot.accept.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.util.Base64Utils;

import java.io.IOException;

/**
 * @author huixing
 * @description
 * @date 2019/12/4
 */
public class HTTPClientUtil{
    public static HttpClient client = new HttpClient();

    public static byte[] doGet(String url, String charset) throws Exception
    {
        GetMethod method = new GetMethod(url);
        method.setDoAuthentication(true);
        int statusCode = client.executeMethod(method);
        byte[] responseBody =
                method.getResponseBody();
//在返回响应消息使用编码(utf-8 或 gb2312)
       // String response = new String(responseBody, "utf-8");
//释放连接
        return responseBody;
    }

    public static void doDelete(String url, String charset) throws IOException {
        DeleteMethod method = new DeleteMethod(url);
        HttpClient client = new HttpClient();
        method.setDoAuthentication(true);
        int statusCode = client.executeMethod(method);

    }
}
