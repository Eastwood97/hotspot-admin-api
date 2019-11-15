package com.jsc.hotspot.common.enums;

/**
 * @author huixing
 * @description 设备种类枚举
 * @date 2019/11/10
 */
public enum DeviceTypeEnum {
    HOT((byte) 0, "热点"), CAMERA((byte) 1, "普通摄像机"), ZNCAMERA((byte) 2, "智能摄像机"), OTHER((byte) 3, "其他设备");

    private byte code;
    private String desc;

    DeviceTypeEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
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
    public static String getType(byte code){
        DeviceTypeEnum[] deviceTypeEnums = values();
        for (DeviceTypeEnum deviceTypeEnum : deviceTypeEnums) {
            if (deviceTypeEnum.getCode() == (code)) {
                return deviceTypeEnum.getDesc();
            }
        }
        return null;
    }
}
