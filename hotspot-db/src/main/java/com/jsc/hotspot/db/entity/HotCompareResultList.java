package com.jsc.hotspot.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Auther: WW
 * @Date: 2019/12/30 0030 11:30
 * @Description:
 */
public class HotCompareResultList {
    private String target_name;
    private String desc;
    private String imsi;
    private String imei;
    private String isdn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate capture_time;

    public String getTarget_name() {
        return target_name;
    }

    public void setTarget_name(String target_name) {
        this.target_name = target_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public LocalDate  getCapture_time() {
        return capture_time;
    }

    public void setCapture_time(LocalDate  capture_time) {
        this.capture_time = capture_time;
    }
}
