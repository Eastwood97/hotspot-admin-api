package com.jsc.hotspot.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 人脸比对底库
 */
@Data
public class MstFaceTargetDb {

    private Integer id;
    private String targetDb;
    private String dbName;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
