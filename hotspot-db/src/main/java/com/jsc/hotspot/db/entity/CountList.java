package com.jsc.hotspot.db.entity;

import com.jsc.hotspot.db.domain.HotNumInfo;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/11/13 0013 09:53
 * @Description:
 */
public class CountList {
    //15天的取号统计
    private List<HotNumInfo> hotNumInfoList;
    //布控总数
    private Long blackNum;
    //归属地饼图
    private List guiShuDi;
    //人脸统计
    private Long peopleFaces;
    //取号总量
    private Long hotNumInfoTotal;
    //今日取号总量
    private Long todayhotNumInfoNum;
    //人脸折线图
    private List cameraCatInfoServiceHoTnumInfoDateNum;

    public CountList(List<HotNumInfo> hotNumInfoList, Long blackNum, List guiShuDi, Long peopleFaces, Long hotNumInfoTotal, Long todayhotNumInfoNum, List cameraCatInfoServiceHoTnumInfoDateNum) {
        this.hotNumInfoList = hotNumInfoList;
        this.blackNum = blackNum;
        this.guiShuDi = guiShuDi;
        this.peopleFaces = peopleFaces;
        this.hotNumInfoTotal = hotNumInfoTotal;
        this.todayhotNumInfoNum = todayhotNumInfoNum;
        this.cameraCatInfoServiceHoTnumInfoDateNum = cameraCatInfoServiceHoTnumInfoDateNum;
    }

    public List<HotNumInfo> getHotNumInfoList() {
        return hotNumInfoList;
    }

    public void setHotNumInfoList(List<HotNumInfo> hotNumInfoList) {
        this.hotNumInfoList = hotNumInfoList;
    }

    public Long getBlackNum() {
        return blackNum;
    }

    public void setBlackNum(Long blackNum) {
        this.blackNum = blackNum;
    }

    public List getGuiShuDi() {
        return guiShuDi;
    }

    public void setGuiShuDi(List guiShuDi) {
        this.guiShuDi = guiShuDi;
    }

    public Long getPeopleFaces() {
        return peopleFaces;
    }

    public void setPeopleFaces(Long peopleFaces) {
        this.peopleFaces = peopleFaces;
    }

    public Long getHotNumInfoTotal() {
        return hotNumInfoTotal;
    }

    public void setHotNumInfoTotal(Long hotNumInfoTotal) {
        this.hotNumInfoTotal = hotNumInfoTotal;
    }

    public Long getTodayhotNumInfoNum() {
        return todayhotNumInfoNum;
    }

    public void setTodayhotNumInfoNum(Long todayhotNumInfoNum) {
        this.todayhotNumInfoNum = todayhotNumInfoNum;
    }

    public List getCameraCatInfoServiceHoTnumInfoDateNum() {
        return cameraCatInfoServiceHoTnumInfoDateNum;
    }

    public void setCameraCatInfoServiceHoTnumInfoDateNum(List cameraCatInfoServiceHoTnumInfoDateNum) {
        this.cameraCatInfoServiceHoTnumInfoDateNum = cameraCatInfoServiceHoTnumInfoDateNum;
    }
}
