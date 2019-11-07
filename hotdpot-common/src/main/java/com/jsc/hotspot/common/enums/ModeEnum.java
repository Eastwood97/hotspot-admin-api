package com.jsc.hotspot.common.enums;

/**
 * @author huixing
 * @description Modemeiju lei
 * @date 2019/11/7
 */
public enum ModeEnum {
    GSM(1, "GSM"), WCDMA(2, "WCDMA"), LTE(3, "LTE");

    private Integer code;
    private String desc;

    ModeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据id获取类型描述
     * @param code
     * @return
     */
    public static String getType(Integer code){
        ModeEnum[] modeEnums = values();
        for (ModeEnum modeEnum : modeEnums) {
            if (modeEnum.getCode().equals(code)) {
                return modeEnum.getDesc();
            }
        }
        return null;
    }
}
