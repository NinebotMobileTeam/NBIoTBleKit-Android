package com.segwaydiscovery.bledemo.util;

import android.text.TextUtils;

import com.sd.blecontrol.bean.BleLog;
import com.segwaydiscovery.bledemo.bean.IoTLog;

/**
 * description 日志处理工具
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/4/14 4:42 PM
 */
public class LogUtil {

    public static String format16(String content) {
        if (TextUtils.isEmpty(content)) {
            return "null";
        }
        return content.toUpperCase().replaceAll("(.{2})", "$1 ");
    }

    public static IoTLog conversion(BleLog log) {
        return new IoTLog(log.getType(), log.getContent());
    }

}
