package com.segwaydiscovery.bledemo.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.segwaydiscovery.bledemo.ActivityRouter;
import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.util.PreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description 配置页
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/3 2:37 PM
 */
@Route(path = ActivityRouter.PAGE_CONFIG)
public class ConfigActivity extends BaseActivity {

    @BindView(R.id.et_device_key)
    EditText etDeviceKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    private void initView() {
        etDeviceKey.setText(PreferencesUtil.getString(Constants.Extra.DEVICE_KEY, "4BKNwi77"));
    }

    @OnClick(R.id.btn_back)
    protected void back() {
        finish();
    }

    @OnClick(R.id.btn_save)
    protected void save() {
        if (etDeviceKey.getText().toString().trim().length() != 8) {
            Toast.makeText(this, "Wrong device key", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesUtil.putString(Constants.Extra.DEVICE_KEY, etDeviceKey.getText().toString().trim());
        finish();
    }
}
