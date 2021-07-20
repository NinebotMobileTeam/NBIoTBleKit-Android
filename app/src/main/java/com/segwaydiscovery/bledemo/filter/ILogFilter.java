package com.segwaydiscovery.bledemo.filter;

import com.sd.blecontrol.bean.Log;

import java.util.List;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/4 4:38 PM
 */
public interface ILogFilter {

    List<Log> filter(List<Log> logList);
}
