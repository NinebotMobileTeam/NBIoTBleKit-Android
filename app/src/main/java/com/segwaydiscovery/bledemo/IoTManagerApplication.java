package com.segwaydiscovery.bledemo;


import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.segwaydiscovery.nbiot.NBIotBle;

/**
 * description IoTManagerApplication
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
        NBIotBle.getInstance().init("dc62c29c55f2445b8e3fe182b7727535", "P20001", true);
//        NBIotBle.getInstance().init("replace your secret", "replace your operate code", true);
    }
}
