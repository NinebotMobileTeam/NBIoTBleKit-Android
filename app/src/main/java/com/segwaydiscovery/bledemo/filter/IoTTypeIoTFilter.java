package com.segwaydiscovery.bledemo.filter;

import com.segwaydiscovery.bledemo.bean.IoT;

import java.util.ArrayList;
import java.util.List;

/**
 * description IoT过滤器
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/4 4:25 PM
 */
public class IoTTypeIoTFilter implements IIoTFilter {

    private int IoTType;

    public IoTTypeIoTFilter(int IOTType) {
        this.IoTType = IOTType;
    }

    @Override
    public List<IoT> filter(List<IoT> ioTList) {
        List<IoT> ioTList1 = new ArrayList<>();
        for (IoT ioT : ioTList) {
            if (ioT.getIoTTypeEnum().getCode() == IoTType) {
                ioTList1.add(ioT);
            }
        }
        return ioTList1;
    }
}
