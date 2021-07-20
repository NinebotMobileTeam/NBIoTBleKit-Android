package com.segwaydiscovery.bledemo.bean;

import com.segwaydiscovery.bledemo.enumation.IoTTypeEnum;

import java.io.Serializable;

/**
 * description IoT
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/4 4:23 PM
 */
public class IoT implements Serializable {

    private String mac;

    private String name;

    private IoTTypeEnum ioTTypeEnum;

    public IoT() {
    }

    public IoT(String mac, String name, IoTTypeEnum ioTTypeEnum) {
        this.mac = mac;
        this.name = name;
        this.ioTTypeEnum = ioTTypeEnum;
    }

    public IoTTypeEnum getIoTTypeEnum() {
        return ioTTypeEnum;
    }

    public void setIoTTypeEnum(IoTTypeEnum ioTTypeEnum) {
        this.ioTTypeEnum = ioTTypeEnum;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IoT{" +
                "mac='" + mac + '\'' +
                ", name='" + name + '\'' +
                ", ioTTypeEnum=" + ioTTypeEnum +
                '}';
    }
}
