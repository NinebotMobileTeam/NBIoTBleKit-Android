package com.segwaydiscovery.bledemo.bean;

/**
 * 11.1 获取固件数据子模型
 * [POST] /launcher/api/bike/bluetooth/upgrade/data
 */
public class BluetoothUpgradeSubPackage {
    private int packageSize;
    private int index;
    private byte[] crc16;
    private byte[] subData;

    public BluetoothUpgradeSubPackage(int packageSize, int index, byte[] crc16, byte[] subData) {
        this.packageSize = packageSize;
        this.index = index;
        this.crc16 = crc16;
        this.subData = subData;
    }

    public int getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public byte[] getCrc16() {
        return crc16;
    }

    public void setCrc16(byte[] crc16) {
        this.crc16 = crc16;
    }

    public byte[] getData() {
        return subData;
    }

    public void setData(byte[] subData) {
        this.subData = subData;
    }
}
