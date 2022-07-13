package com.segwaydiscovery.bledemo.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.segwaydiscovery.nbiot.BluetoothKit;
import com.segwaydiscovery.nbiot.HelmetLockKit;
import com.segwaydiscovery.nbiot.bean.HelmetLockInformation;
import com.segwaydiscovery.nbiot.bean.HelmetLockStatus;
import com.segwaydiscovery.nbiot.bean.QueryIoTInfomation;
import com.segwaydiscovery.nbiot.bean.QueryVehicleInformation;
import com.segwaydiscovery.nbiot.interfaces.ConnectionState;
import com.segwaydiscovery.nbiot.interfaces.LogType;
import com.segwaydiscovery.nbiot.interfaces.OnConnectionStateChangeListener;
import com.segwaydiscovery.nbiot.interfaces.OnLockHelmetListener;
import com.segwaydiscovery.nbiot.interfaces.OnLockListener;
import com.segwaydiscovery.nbiot.interfaces.OnOpenBatteryCoverListener;
import com.segwaydiscovery.nbiot.interfaces.OnOpenSaddleListener;
import com.segwaydiscovery.nbiot.interfaces.OnOpenTailBoxListener;
import com.segwaydiscovery.nbiot.interfaces.OnQueryHelmetLockInfoListener;
import com.segwaydiscovery.nbiot.interfaces.OnQueryHelmetLockStatusListener;
import com.segwaydiscovery.nbiot.interfaces.OnQueryIoTInfoListener;
import com.segwaydiscovery.nbiot.interfaces.OnQueryVehicleInfoListener;
import com.segwaydiscovery.nbiot.interfaces.OnUnlockHelmetListener;
import com.segwaydiscovery.nbiot.interfaces.OnUnlockListener;
import com.segwaydiscovery.bledemo.ActivityRouter;
import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.adapter.LogAdapter;
import com.segwaydiscovery.bledemo.bean.IoTLog;
import com.segwaydiscovery.bledemo.dialog.CommandDialog;
import com.segwaydiscovery.bledemo.util.PreferencesUtil;

import java.util.Collections;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/3 2:37 PM
 */
@Route(path = ActivityRouter.PAGE_CONTROL)
public class ControlActivity extends BaseActivity {

    @Autowired(name = Constants.Extra.DEVICE_MAC)
    String deviceMac;
    @Autowired(name = Constants.Extra.DEVICE_KEY)
    String deviceKey;
    @Autowired(name = Constants.Extra.DEVICE_IMEI)
    String deviceIMEI;
    @Autowired(name = Constants.Extra.DEVICE_TYPE)
    int deviceType;

    BluetoothKit bluetoothControl;
    HelmetLockKit helmetLockKit;

    @BindView(R.id.rv_log)
    RecyclerView rvLog;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private LogAdapter logAdapter;

    private LinearLayoutManager logLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_control;
    }

    private void initView() {
        hideStatusbar();
        tvTitle.setText("IoT:" + deviceMac);
        initRv();

        if (deviceType == 1) {
            initBle();
        } else {
            initHelmet();
        }

    }

    private void initHelmet() {
        helmetLockKit = new HelmetLockKit();
        helmetLockKit.init(this);
        helmetLockKit.connect(deviceMac, deviceKey, new OnConnectionStateChangeListener() {
            @Override
            public void onConnectionStateChange(int state) {
                switch (state) {
                    case ConnectionState.STATE_CONNECTED:
                        addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "Helmet Connected!"));
                        break;
                    case ConnectionState.STATE_DISCONNECTED:
                        addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "Helmet Disconnected!"));
                        break;
                    default:
                        addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "Helmet ConnectFailed!"));

                }
            }
        });
    }


    private void initRv() {
        logLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        logLayoutManager.setStackFromEnd(true);
        rvLog.setLayoutManager(logLayoutManager);

        logAdapter = new LogAdapter(this);
        rvLog.setAdapter(logAdapter);
        logAdapter.setItems(Collections.singletonList(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "Connecting...")));
    }

    private void hideStatusbar() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    private void initBle() {
        bluetoothControl = new BluetoothKit();
        bluetoothControl.init(this);
        bluetoothControl.connect(deviceMac, deviceKey, deviceIMEI, new OnConnectionStateChangeListener() {
            @Override
            public void onConnectionStateChange(int state) {
                switch (state) {
                    case ConnectionState.STATE_CONNECTED:
                        addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "Connected!"));
                        break;
                    case ConnectionState.STATE_DISCONNECTED:
                        addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "Disconnected!"));
                        break;
                    default:
                        addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "ConnectFailed!"));
                }
            }
        });
    }

    private void addNormalLog(IoTLog ioTLog) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logAdapter.addItems(Collections.singletonList(ioTLog));
                logLayoutManager.scrollToPositionWithOffset(logAdapter.getItemCount() - 1, Integer.MIN_VALUE);

            }
        });
    }

    @OnClick(R.id.tv_command_dialog)
    protected void command() {
        CommandDialog.create(this)
                .setDeviceType(deviceType)
                .setOnCommandDialogClickListener(command -> {
                    switch (command.getCommand()) {
                        case 1:
                            bluetoothControl.unLock(new OnUnlockListener() {
                                @Override
                                public void onUnlockSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onUnlockSuccess!"));
                                }

                                @Override
                                public void onUnlockFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onUnlockFail--" + code + "--" + msg));
                                }
                            });

                            break;
                        case 2:
                            bluetoothControl.lock(new OnLockListener() {
                                @Override
                                public void onLockSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onLockSuccess!"));
                                }

                                @Override
                                public void onLockFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onLockFail!--" + code + "--" + msg));
                                }
                            });
                            break;
                        case 3:
                            bluetoothControl.queryIoTInformation(new OnQueryIoTInfoListener() {
                                @Override
                                public void onQueryIoTInfoSuccess(QueryIoTInfomation queryIoTInfomation) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "LockInfo--"
                                            + "highBatteryVoltage-" + queryIoTInfomation.getHighBatteryVoltage()
                                            + "  lowBatteryVoltage-" + queryIoTInfomation.getLowBatteryVoltage()
                                            + "  powerStatus-" + queryIoTInfomation.getPowerStatus()
                                            + "  reserve-" + queryIoTInfomation.getReserve()
                                            + "  majorVersionNumber-" + queryIoTInfomation.getMajorVersionNumber()
                                            + "  minorVersionNumber-" + queryIoTInfomation.getMinorVersionNumber()
                                            + "  versionRevisions-" + queryIoTInfomation.getVersionRevisions()
                                            + "  voltage: " + queryIoTInfomation.getVoltage()
                                            + "  isLocked: " + queryIoTInfomation.isLocked()));
                                }

                                @Override
                                public void onQueryIoTInfoFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onQueryIoTInfoFail!--" + code + "--" + msg));
                                }
                            });
                            break;
                        case 4:
                            bluetoothControl.queryVehicleInformation(new OnQueryVehicleInfoListener() {
                                @Override
                                public void onQueryVehicleInfoSuccess(QueryVehicleInformation queryVehicleInformation) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "VehicleInfo--"
                                            + "versionRevisions-" + queryVehicleInformation.getCurrentBatteryLevel()
                                            + "  currentMode-" + queryVehicleInformation.getCurrentMode()
                                            + "  currentSpeed-" + queryVehicleInformation.getCurrentSpeed()
                                            + "  singleMileage-" + queryVehicleInformation.getSingleMileage()
                                            + "  range-" + queryVehicleInformation.getRange()));
                                }

                                @Override
                                public void onQueryVehicleInfoFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onOnQueryVehicleInfoFail!--" + code + "--" + msg));
                                }
                            });
                            break;
                        case 5:
                            bluetoothControl.openBatteryCover(new OnOpenBatteryCoverListener() {
                                @Override
                                public void OnOpenBatteryCoverSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "OnUnlockBatteryCoverSuccess!"));
                                }

                                @Override
                                public void OnOpenBatteryCoverFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "OnUnlockBatteryCoverFail!--" + code + "--" + msg));
                                }
                            });
                            break;
                        case 6:
                            bluetoothControl.openSaddle(new OnOpenSaddleListener() {
                                @Override
                                public void onOpenSaddleSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onMechLockControlSuccess!"));
                                }

                                @Override
                                public void onOpenSaddleFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onMechLockControlFail!--" + code + "--" + msg));
                                }
                            });
                            break;
                        case 7:
                            bluetoothControl.openTailBox(new OnOpenTailBoxListener() {
                                @Override
                                public void onOpenTailBoxSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onMechLockControlSuccess!"));
                                }

                                @Override
                                public void onOpenTailBoxFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onMechLockControlFail!--" + code + "--" + msg));
                                }
                            });
                            break;
                        case 8:
                            bluetoothControl.unLockHelmet(new OnUnlockHelmetListener() {
                                @Override
                                public void onUnlockHelmetSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onUnLockHelmetSuccess!"));
                                }

                                @Override
                                public void onUnlockHelmetFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onUnLockHelmetFail!--" + code + "--" + msg));
                                }
                            });
                            break;
                        case 9:
                            bluetoothControl.lockHelmet(new OnLockHelmetListener() {
                                @Override
                                public void onLockHelmetSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onLockHelmetSuccess!"));

                                }

                                @Override
                                public void onLockHelmetFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onLockHelmetFail!--" + code + "--" + msg));

                                }
                            });
                            break;

                        case -1:
                            helmetLockKit.unLock(new OnUnlockHelmetListener() {
                                @Override
                                public void onUnlockHelmetSuccess() {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onUnlockHelmetSuccess!"));

                                }

                                @Override
                                public void onUnlockHelmetFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onUnlockHelmetFail!--code:" + code + ",msg:" + msg));

                                }
                            });
                            break;
                        case -2:
                            addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL,
                                    "假装是个loading。。。"));
                            helmetLockKit.queryLockInfo(new OnQueryHelmetLockInfoListener() {
                                @Override
                                public void onQueryHelmetLockInfoSuccess(HelmetLockInformation helmetLockInformation) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL,
                                            "HelmetInfo:\n   "
                                                    + "powerPercent: " + helmetLockInformation.getPowerPercent() + "\n   "
                                                    + "firmwareVersion: " + helmetLockInformation.getFirmwareVersion() + "\n   "));

                                }

                                @Override
                                public void onQueryHelmetLockInfoFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onQueryHelmetLockInfoFail!--code" + code + "msg--" + msg));
                                }
                            });
                            break;
                        case -3:
                            helmetLockKit.queryLockStatus(new OnQueryHelmetLockStatusListener() {
                                @Override
                                public void onQueryHelmetLockStatusSuccess(HelmetLockStatus helmetLockStatus) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL,
                                            "HelmetStatus:\n   "
                                                    + "voltage: " + helmetLockStatus.getVoltage() + "\n   "
                                                    + "isLocked: " + helmetLockStatus.isLocked()));

                                }

                                @Override
                                public void onQueryHelmetLockStatusFail(int code, String msg) {
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "onQueryHelmetLockStatusFail!--code" + code + "msg--" + msg));

                                }
                            });
                            break;
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetoothControl != null) {
            bluetoothControl.disConnect();
        }
        if (helmetLockKit != null) {
            helmetLockKit.disConnect();
        }
    }
}
