package com.segwaydiscovery.bledemo.filter;

import com.segwaydiscovery.bledemo.bean.IoT;

import java.util.List;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/4 4:25 PM
 */
public interface IIoTFilter {

    List<IoT> filter(List<IoT> ioTList);
}
