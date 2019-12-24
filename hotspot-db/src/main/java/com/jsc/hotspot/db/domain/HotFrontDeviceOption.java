package com.jsc.hotspot.db.domain;

/**
 * @Auther: WW
 * @Date: 2019/12/23 0023 15:56
 * @Description:
 */
public class HotFrontDeviceOption {
    private Integer id;
    private String value;
    private Integer devId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }
}
