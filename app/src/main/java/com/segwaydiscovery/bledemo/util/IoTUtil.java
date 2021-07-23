package com.segwaydiscovery.bledemo.util;

import android.bluetooth.BluetoothDevice;

import com.segwaydiscovery.bledemo.bean.IoT;

public class IoTUtil {

    public static IoT createIoT(BluetoothDevice bluetoothDevice) {
        return new IoT(bluetoothDevice.getAddress(), bluetoothDevice.getName());
    }

}
