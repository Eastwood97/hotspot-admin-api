package com.jsc.hotspot.api.utils;

import com.jsc.hotspot.api.config.UrlConst;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

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
        PostMethod postMethod = new PostMethod(url);
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
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *   发送请求的URL
     * @param param
     *   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String param, String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString;
            if(!param.equals("")) {
                urlNameString= url + "?" + param;
            }else{
                 urlNameString = url;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
