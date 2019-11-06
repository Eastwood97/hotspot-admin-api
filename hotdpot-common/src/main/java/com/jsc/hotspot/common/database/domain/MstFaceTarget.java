package com.jsc.hotspot.common.database.domain;

import lombok.Data;

import java.util.Date;

/**
 * 人脸目标
 */
@Data
public class MstFaceTarget {

    private Integer id;
    private String targetDb;
    private String targetId;
    private Integer branchNum;
    private String imgId;
    private String alias;
    private String description;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
