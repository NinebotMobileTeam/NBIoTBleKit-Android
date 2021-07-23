package com.segwaydiscovery.bledemo.util;

import android.text.TextUtils;

import com.segwaydiscovery.nbiot.bean.BleLog;
import com.segwaydiscovery.bledemo.bean.IoTLog;

public class LogUtil {

    public static String format16(String content) {
        if (TextUtils.isEmpty(content)) {
            return "null";
        }
        return content.toUpperCase().replaceAll("(.{2})", "$1 ");
    }


}
