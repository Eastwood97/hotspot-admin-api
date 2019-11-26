package com.jsc.hotspot.db.domain;

/**
 * @Auther: WW
 * @Date: 2019/11/26 0026 14:40
 * @Description:
 */
public class ImsiArea {
    private Integer MCC;
    private String ISO;
    private String COUNTRY;
    private String COUNTRY_ENG;
    private Integer COUNTRY_CODE;

    public ImsiArea(Integer MCC, String ISO, String COUNTRY, String COUNTRY_ENG, Integer COUNTRY_CODE) {
        this.MCC = MCC;
        this.ISO = ISO;
        this.COUNTRY = COUNTRY;
        this.COUNTRY_ENG = COUNTRY_ENG;
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    public Integer getMCC() {
        return MCC;
    }

    public void setMCC(Integer MCC) {
        this.MCC = MCC;
    }

    public String getISO() {
        return ISO;
    }

    public void setISO(String ISO) {
        this.ISO = ISO;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getCOUNTRY_ENG() {
        return COUNTRY_ENG;
    }

    public void setCOUNTRY_ENG(String COUNTRY_ENG) {
        this.COUNTRY_ENG = COUNTRY_ENG;
    }

    public Integer getCOUNTRY_CODE() {
        return COUNTRY_CODE;
    }

    public void setCOUNTRY_CODE(Integer COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }
}
