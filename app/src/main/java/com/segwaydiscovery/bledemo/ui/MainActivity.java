package com.segwaydiscovery.bledemo.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.sd.blecontrol.BluetoothKit;
import com.sd.blecontrol.interfaces.ConnectionState;
import com.sd.blecontrol.interfaces.OnConnectionStateChangeListener;
import com.segwaydiscovery.bledemo.ActivityRouter;
import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.et_device_mac)
    EditText etDeviceMac;

    private String deviceMac;


    @OnClick(R.id.btn_connect)
    public void connect() {
        if (!TextUtils.isEmpty(deviceMac)) {
            ARouter.getInstance().build(ActivityRouter.PAGE_CONTROL)
                    .withString(Constants.Extra.DEVICE_MAC, deviceMac)
                    .navigation(MainActivity.this);
        }

    }

    @OnClick(R.id.btn_to_scan)
    public void toScan() {
        ARouter.getInstance().build(ActivityRouter.PAGE_SCAN)
                .navigation(MainActivity.this);
    }

    @OnClick(R.id.iv_config)
    protected void config() {
        ARouter.getInstance().build(ActivityRouter.PAGE_CONFIG)
                .navigation(MainActivity.this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.BLUETOOTH
                , Manifest.permission.BLUETOOTH_ADMIN)
                .subscribe(
                        aBoolean -> Log.e("permission", "---" + aBoolean));
    }

    private void initViews() {
        etDeviceMac.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    deviceMac = editable.toString();
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}