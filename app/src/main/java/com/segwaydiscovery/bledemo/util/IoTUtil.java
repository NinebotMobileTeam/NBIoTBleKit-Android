package com.segwaydiscovery.bledemo.util;

import android.bluetooth.BluetoothDevice;

import com.segwaydiscovery.bledemo.bean.IoT;
import com.segwaydiscovery.bledemo.enumation.IoTTypeEnum;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/4 5:03 PM
 */
public class IoTUtil {

    public static IoT creatIoT(BluetoothDevice bluetoothDevice) {
        return new IoT(bluetoothDevice.getAddress(), bluetoothDevice.getName(), getType(bluetoothDevice.getName()));
    }

    private static IoTTypeEnum getType(String name) {
        if (name.contains("Segway IoT")) {
            return IoTTypeEnum.IOT_TYPE_ONMI;
        }

        if (name.contains("ZK600") || name.contains("SD_MaxPlus")) {
            return IoTTypeEnum.IOT_TYPE_YIWEI;
        }

        return IoTTypeEnum.IOT_TYPE_UNKNOW;
    }

}
