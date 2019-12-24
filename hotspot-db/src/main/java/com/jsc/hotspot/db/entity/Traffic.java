package com.jsc.hotspot.db.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Auther: WW
 * @Date: 2019/11/29 0029 17:27
 * @Description:
 */
public class Traffic implements Serializable {
    private Integer devId;
    private Integer area;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String[] createTime;

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String[] getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String[] createTime) {
        this.createTime = createTime;
    }
}
