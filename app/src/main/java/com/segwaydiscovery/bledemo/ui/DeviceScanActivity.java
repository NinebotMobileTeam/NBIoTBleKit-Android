package com.segwaydiscovery.bledemo.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.segwaydiscovery.nbiot.BluetoothKit;
import com.segwaydiscovery.nbiot.interfaces.OnDeviceFindListener;
import com.segwaydiscovery.bledemo.ActivityRouter;
import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.adapter.BaseAdapter;
import com.segwaydiscovery.bledemo.adapter.IoTListAdapter;
import com.segwaydiscovery.bledemo.util.IoTUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description 设备扫描
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/2/4 4:24 PM
 */
@Route(path = ActivityRouter.PAGE_SCAN)
public class DeviceScanActivity extends BaseActivity {

    @BindView(R.id.rv_device)
    RecyclerView rvDevice;

    @Autowired(name = Constants.Extra.DEVICE_KEY)
    String deviceKey;
    @Autowired(name = Constants.Extra.DEVICE_IMEI)
    String deviceIMEI;
    @Autowired(name = Constants.Extra.DEVICE_TYPE)
    int deviceType;

    BluetoothKit bluetoothKit;

    private IoTListAdapter ioTListAdapter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.BLUETOOTH
                , Manifest.permission.BLUETOOTH_ADMIN)
                .subscribe(
                        aBoolean -> initBle());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_scan;
    }

    private void initView() {

        ioTListAdapter = new IoTListAdapter(this);
        ioTListAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ARouter.getInstance().build(ActivityRouter.PAGE_CONTROL)
                        .withString(Constants.Extra.DEVICE_MAC, ioTListAdapter.getItem(position).getMac())
                        .withString(Constants.Extra.DEVICE_KEY, deviceKey)
                        .withString(Constants.Extra.DEVICE_IMEI, deviceIMEI)
                        .withInt(Constants.Extra.DEVICE_TYPE, deviceType)
                        .navigation(DeviceScanActivity.this);
                Log.d("IoT", ioTListAdapter.getItem(position).toString());
            }
        });
        rvDevice.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        rvDevice.setAdapter(ioTListAdapter);
    }

    private void initBle() {
        bluetoothKit = new BluetoothKit();
        bluetoothKit.init(this);
        bluetoothKit.scanDevice(new OnDeviceFindListener() {
            @Override
            public void onFindDevice(BluetoothDevice device) {
                ioTListAdapter.addItems(Collections.singletonList(IoTUtil.createIoT(device)));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetoothKit != null) {
            bluetoothKit.disConnect();
        }
    }
}
