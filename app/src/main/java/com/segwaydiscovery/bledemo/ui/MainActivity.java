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
import com.segwaydiscovery.nbiot.BluetoothKit;
import com.segwaydiscovery.nbiot.interfaces.ConnectionState;
import com.segwaydiscovery.nbiot.interfaces.OnConnectionStateChangeListener;
import com.segwaydiscovery.bledemo.ActivityRouter;
import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.et_device_mac)
    EditText etDeviceMac;
    @BindView(R.id.et_device_key)
    EditText etDeviceKey;
    @BindView(R.id.et_device_imei)
    EditText etDeviceIMEI;

    private String deviceMac;
    private String deviceKey;
    private String deviceIMEI;


    @OnClick(R.id.btn_connect)
    public void connect() {

        deviceMac = etDeviceMac.getText().toString().trim();
        deviceKey = etDeviceKey.getText().toString().trim();
        deviceIMEI = etDeviceIMEI.getText().toString().trim();
        if (!TextUtils.isEmpty(deviceMac) && !TextUtils.isEmpty(deviceKey) && !TextUtils.isEmpty(deviceIMEI)) {
            ARouter.getInstance().build(ActivityRouter.PAGE_CONTROL)
                    .withString(Constants.Extra.DEVICE_MAC, deviceMac)
                    .withString(Constants.Extra.DEVICE_KEY, deviceKey)
                    .withString(Constants.Extra.DEVICE_IMEI, deviceIMEI)
                    .navigation(MainActivity.this);
        } else {
            Toast.makeText(MainActivity.this, "deviceMac,deviceKey and IMEI can't be empty", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_to_scan)
    public void toScan() {
        deviceKey = etDeviceKey.getText().toString().trim();
        deviceIMEI = etDeviceIMEI.getText().toString().trim();
        if (!TextUtils.isEmpty(deviceKey) && !TextUtils.isEmpty(deviceIMEI)) {
            ARouter.getInstance().build(ActivityRouter.PAGE_SCAN)
                    .withString(Constants.Extra.DEVICE_KEY, deviceKey)
                    .withString(Constants.Extra.DEVICE_IMEI, deviceIMEI)
                    .navigation(MainActivity.this);
        } else {
            Toast.makeText(MainActivity.this, "IMEI can't be empty", Toast.LENGTH_SHORT).show();
        }

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
        deviceKey = "4BKNwi77";
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
        etDeviceKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    deviceKey = editable.toString();
                }
            }
        });
        etDeviceIMEI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    deviceIMEI = editable.toString();
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
