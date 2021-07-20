package com.segwaydiscovery.bledemo;


import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sd.blecontrol.NBIotBle;

/**
 * description IoTmanagerApplacition
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/3 2:51 PM
 */
public class IoTManagerApplication extends Application {

    private static IoTManagerApplication instance;

    public static IoTManagerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ARouter.init(this);
        NBIotBle.getInstance().init("dc62c29c55f2445b8e3fe182b7727535", "P20001", false);
    }
}
