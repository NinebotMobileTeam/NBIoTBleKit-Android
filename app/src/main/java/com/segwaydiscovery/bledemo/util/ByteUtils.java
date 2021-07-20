package com.segwaydiscovery.bledemo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dandenion on 2020/06/02
 */
public class ByteUtils {

    /**
     * 分包
     *
     * @param bytes
     * @param packageIndex
     * @param packageSize
     * @return
     */
    public static byte[] spitBytes(byte[] bytes, Integer packageIndex, int packageSize) {
        if (null != bytes && bytes.length > packageIndex * packageSize) {
            int from = packageIndex * packageSize;
            int to = (from + packageSize) > bytes.length ? bytes.length : (from + packageSize);
            return Arrays.copyOfRange(bytes, from, to);
        }
        return new byte[0];
    }

    public static List<byte[]> spitBytes(byte[] oriBytes, int packagesSize) {
        List<byte[]> result = new ArrayList<>();
        for (int i = 0; i < oriBytes.length / packagesSize; i++) {
            result.add(Arrays.copyOfRange(oriBytes, i * packagesSize, (i + 1) * packagesSize));
        }
        return result;
    }

    /**
     * 将整数转换为byte数组并指定长度
     * -128到127
     *
     * @param a      整数
     * @param length 指定长度
     * @return
     */
    public static byte[] getBytesFromInt(int a, int length) {
        byte[] bytes = new byte[length];
        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte) (a % 255);
            a = a / 255;
        }
        return bytes;
    }

    /**
     * 将byte数组转换为整数
     * -128到127
     *
     * @param bytes
     * @return
     */
    public static int getIntFromBytes(byte[] bytes, int length) {
        int a = 0;
        for (int i = bytes.length - 1; i >= 0; i--) {
            a += bytes[i] * Math.pow(255, bytes.length - i - 1);
        }
        return a;
    }

    /**
     * 将2字节的字节数组转换成整型
     * 0到255
     *
     * @param byte_high
     * @param byte_low
     * @return
     */
    public static int getIntFromTwinBytes(byte byte_high, byte byte_low) {
        byte[] bytes = new byte[2];
        bytes[0] = byte_high;
        bytes[1] = byte_low;

        return getIntFromTwinBytes(bytes);
    }

    /**
     * 将2字节的字节数组转换成整型
     * 0到255
     *
     * @param bytes
     * @return
     */
    public static int getIntFromTwinBytes(byte[] bytes) {
        int high = bytes[0];
        int low = bytes[1];

        if (high < 0) {
            high = high + 256;
        }

        if (low < 0) {
            low = low + 256;
        }

        return high * 256 + low;
    }

    /**
     * 将整型转换成2字节的字节数组
     * 0到255
     *
     * @param num
     * @return
     */
    public static byte[] getTwinBytesFromInt(int num) {
        byte[] result = new byte[2];
        int high = num / 256;
        int low = num % 256;

        if (high > 127) {
            high = high - 256;
        }

        if (low > 127) {
            low = low - 256;
        }

        result[0] = (byte) high;
        result[1] = (byte) low;

        return result;
    }

    public static byte[] intToBytes(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static byte[] stringToBytes(String value) {
        byte[] tag = new byte[4];
        tag[0] = value.getBytes()[0];
        tag[1] = value.getBytes()[1];
        tag[2] = value.getBytes()[2];
        tag[3] = value.getBytes()[3];
        return tag;
    }
}
