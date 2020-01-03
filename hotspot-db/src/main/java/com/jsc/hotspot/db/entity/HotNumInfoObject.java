package com.jsc.hotspot.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: WW
 * @Date: 2019/12/27 0027 11:16
 * @Description:
 */
public class HotNumInfoObject {
    private Integer id;
    private Integer devId;
    private String imsi;
    private String imei;
    private String area;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    private String shengshi;
    private String location;
    private Integer num;

    public HotNumInfoObject(Integer id, Integer devId, String imsi, String imei, String area, String shengshi, String location, Integer num) {
        this.id = id;
        this.devId = devId;
        this.imsi = imsi;
        this.imei = imei;
        this.area = area;
        this.shengshi = shengshi;
        this.location = location;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShengshi() {
        return shengshi;
    }

    public void setShengshi(String shengshi) {
        this.shengshi = shengshi;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
