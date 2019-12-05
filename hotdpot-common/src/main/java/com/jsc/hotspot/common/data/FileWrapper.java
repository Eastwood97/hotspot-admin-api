package com.jsc.hotspot.common.data;

import lombok.Data;

import java.io.InputStream;

/**
 * @author huixing
 * @description
 * @date 2019/12/4
 */
@Data
public class FileWrapper {
    private String fileName;
    private String fileBase64;
    private String description;
    private byte[] fileContent;
    private InputStream fileInputStream;

}
