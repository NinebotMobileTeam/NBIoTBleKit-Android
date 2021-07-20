package com.segwaydiscovery.bledemo.enumation;

import com.segwaydiscovery.bledemo.R;

/**
 * description 日志类型
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/8 7:43 PM
 */
public enum LogEnum {
    LOG_TYPE_UNKNOW(0, "", R.color.color318),
    LOG_TYPE_APP_TO_IOT(1, "[App->IoT]", R.color.colorDE7),
    LOG_TYPE_IOT_TO_APP(2, "[IoT->App]", R.color.color63D),
    LOG_TYPE_IOT_TO_NORMAL(3, "[Debug]", R.color.colorB1D),
    LOG_TYPE_DESCRIPTION(4, "[Description]", R.color.colorB1D),
    LOG_TYPE_IOT(5, "", R.color.colorB1D);

    private int type;

    private String tag;

    private int textColor;

    LogEnum(int type, String tag, int textColor) {
        this.type = type;
        this.tag = tag;
        this.textColor = textColor;
    }

    public static LogEnum getEnum(int code) {
        for (LogEnum logEnum : LogEnum.values()) {
            if (code == logEnum.type) {
                return logEnum;
            }
        }
        return LOG_TYPE_UNKNOW;
    }

    public int getType() {
        return type;
    }

    public int getTextColor() {
        return textColor;
    }

    public String getTag() {
        return tag;
    }
}
