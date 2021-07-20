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
import com.sd.blecontrol.BluetoothKit;
import com.sd.blecontrol.bean.QueryIoTInfomation;
import com.sd.blecontrol.bean.QueryVehicleInformation;
import com.sd.blecontrol.interfaces.ConnectionState;
import com.sd.blecontrol.interfaces.LogType;
import com.sd.blecontrol.interfaces.OnConnectionStateChangeListener;
import com.sd.blecontrol.interfaces.OnLockListener;
import com.sd.blecontrol.interfaces.OnOpenBatteryCoverListener;
import com.sd.blecontrol.interfaces.OnOpenSaddleListener;
import com.sd.blecontrol.interfaces.OnOpenTailBoxListener;
import com.sd.blecontrol.interfaces.OnQueryIoTInfoListener;
import com.sd.blecontrol.interfaces.OnQueryVehicleInfoListener;
import com.sd.blecontrol.interfaces.OnUnlockListener;
import com.segwaydiscovery.bledemo.ActivityRouter;
import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.adapter.LogAdapter;
import com.segwaydiscovery.bledemo.bean.FirmwareBean;
import com.segwaydiscovery.bledemo.bean.IoTLog;
import com.segwaydiscovery.bledemo.dialog.CommandDialog;
import com.segwaydiscovery.bledemo.util.PreferencesUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    String mac;

    BluetoothKit bluetoothControl;

    @BindView(R.id.rv_log)
    RecyclerView rvLog;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private LogAdapter logAdapter;

    private LinearLayoutManager logLayoutmanager;
    private List<FirmwareBean> firmwareBeans = new ArrayList<>();

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
        tvTitle.setText("IoT:" + mac);
        initRv();
        initBle();
    }


    private void initRv() {
        logLayoutmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        logLayoutmanager.setStackFromEnd(true);
        rvLog.setLayoutManager(logLayoutmanager);

        logAdapter = new LogAdapter(this);
        rvLog.setAdapter(logAdapter);
        logAdapter.setItems(Collections.singletonList(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "Connecting...")));
    }

    private void hideStatusbar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initBle() {
        bluetoothControl = new BluetoothKit();
        bluetoothControl.init(this);
        bluetoothControl.connect(mac, PreferencesUtil.getString(Constants.Extra.DEVICE_KEY, "4BKNwi77"), new OnConnectionStateChangeListener() {
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetoothControl != null) {
            bluetoothControl.disConnect();
        }
    }

    private void addNormalLog(IoTLog ioTLog) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logAdapter.addItems(Collections.singletonList(ioTLog));
                logLayoutmanager.scrollToPositionWithOffset(logAdapter.getItemCount() - 1, Integer.MIN_VALUE);

            }
        });
    }

    @OnClick(R.id.tv_command_dialog)
    protected void command(View view) {
        switch (view.getId()) {
            case R.id.tv_command_dialog:
                CommandDialog.create(this).setOnCommandDialogClickListener(command -> {
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
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "锁信息---onLockMsgSuccess: "
                                            + "高电压-" + queryIoTInfomation.getHighBatteryVoltage()
                                            + "  低电压-" + queryIoTInfomation.getLowBatteryVoltage()
                                            + "  开/关锁-" + queryIoTInfomation.getPowerStatus()
                                            + "  预留-" + queryIoTInfomation.getReserve()
                                            + "  主版本-" + queryIoTInfomation.getMajorVersionNumber()
                                            + "  次版本-" + queryIoTInfomation.getMinorVersionNumber()
                                            + "  修改次数-" + queryIoTInfomation.getVersionRevisions()));
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
                                    addNormalLog(new IoTLog(LogType.LOG_TYPE_IOT_TO_NORMAL, "滑板车信息---onScooterMsgSuccess: 成功---"
                                            + "当前电量-" + queryVehicleInformation.getCurrentBatteryLevel()
                                            + "  模式-" + queryVehicleInformation.getCurrentMode()
                                            + "  车速-" + queryVehicleInformation.getCurrentSpeed()
                                            + "  单次-" + queryVehicleInformation.getSingleMileage()
                                            + "  续航-" + queryVehicleInformation.getRange()));
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
                    }
                }).show();
                break;
        }
    }

}
