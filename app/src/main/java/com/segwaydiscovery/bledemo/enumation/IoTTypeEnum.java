package com.segwaydiscovery.bledemo.enumation;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/4 4:33 PM
 */
public enum IoTTypeEnum {
    IOT_TYPE_UNKNOW(0, ""),
    IOT_TYPE_ONMI(1, "Onmi/Ninebot"),
    IOT_TYPE_YIWEI(2, "Yiwei");

    private int code;

    private String name;

    IoTTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static IoTTypeEnum getEnum(int code) {
        for (IoTTypeEnum ioTTypeEnum : IoTTypeEnum.values()) {
            if (code == ioTTypeEnum.code) {
                return ioTTypeEnum;
            }
        }
        return IOT_TYPE_UNKNOW;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
