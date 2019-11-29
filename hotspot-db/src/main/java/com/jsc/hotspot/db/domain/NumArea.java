package com.jsc.hotspot.db.domain;

/**
 * @Auther: WW
 * @Date: 2019/11/26 0026 09:14
 * @Description:
 */
public class NumArea {
    private Integer id;
    private Integer Mobile;
    private String Province ;
    private String City;
    private  String Corp ;
    private Integer AreaCode;
    private Integer PostCode;

    public NumArea(Integer mobile, String province, String city, String corp, Integer areaCode, Integer postCode) {
        this.Mobile = mobile;
        this. Province = province;
        this.City = city;
        this. Corp = corp;
        this. AreaCode = areaCode;
        this. PostCode = postCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMobile() {
        return Mobile;
    }

    public void setMobile(Integer mobile) {
        Mobile = mobile;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCorp() {
        return Corp;
    }

    public void setCorp(String corp) {
        Corp = corp;
    }

    public Integer getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(Integer areaCode) {
        AreaCode = areaCode;
    }

    public Integer getPostCode() {
        return PostCode;
    }

    public void setPostCode(Integer postCode) {
        PostCode = postCode;
    }
}
