package com.jsc.hotspot.accept.sdk;

import java.io.UnsupportedEncodingException;

/**
 * @author huixing
 * @description 路径
 * @date 2019/12/3
 */
public class Path {

    public static String DLL_PATH;

    static {
        String path = (Path.class.getResource("/").getPath()).replaceAll("%20", " ").substring(1).replace("/", "\\");
//        try {
            DLL_PATH = "D:\\Repositories\\hotspot-admin-api\\hotspot-accept\\target\\classes";
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }
}
