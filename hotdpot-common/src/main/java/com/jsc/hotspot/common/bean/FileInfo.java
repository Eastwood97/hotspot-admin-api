package com.jsc.hotspot.common.bean;

import lombok.Data;

import java.io.InputStream;

/**
 * @author huixing
 * @description 文件信息
 * @date 2019/12/26
 */
@Data
public class FileInfo {
    // 文件流
    private String url;

    // 目標名字
    private String targetName;

    // 描述
    private String describe;
}
