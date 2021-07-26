package com.segwaydiscovery.bledemo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.segwaydiscovery.bledemo.ActivityRouter;
import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.util.PreferencesUtil;
import com.segwaydiscovery.nbiot.NBIotBle;

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

    @BindView(R.id.et_secret)
    EditText etSecret;
    @BindView(R.id.et_operator_code)
    EditText etOperatorCode;

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
        etSecret.setText(PreferencesUtil.getString(Constants.Extra.SECRET, ""));
        etOperatorCode.setText(PreferencesUtil.getString(Constants.Extra.OPERATOR_CODE, ""));
    }

    @OnClick(R.id.btn_back)
    protected void back() {
        finish();
    }

    @OnClick(R.id.btn_save)
    protected void save() {

        if (TextUtils.isEmpty(etSecret.getText().toString().trim())) {
            Toast.makeText(this, "Secret should not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(etSecret.getText().toString().trim())) {
            Toast.makeText(this, "OperatorCode should not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesUtil.putString(Constants.Extra.SECRET, etSecret.getText().toString().trim());
        PreferencesUtil.putString(Constants.Extra.OPERATOR_CODE, etOperatorCode.getText().toString().trim());

        NBIotBle.getInstance().init(etSecret.getText().toString().trim(), etOperatorCode.getText().toString().trim(), true);
    }
}
