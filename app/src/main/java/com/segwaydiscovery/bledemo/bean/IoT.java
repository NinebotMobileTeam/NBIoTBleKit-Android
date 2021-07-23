package com.segwaydiscovery.bledemo.bean;

import java.io.Serializable;

/**
 * description IoT
 *
 */
public class IoT implements Serializable {

    private String mac;

    private String name;


    public IoT() {
    }

    public IoT(String mac, String name) {
        this.mac = mac;
        this.name = name;
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
                '}';
    }
}
