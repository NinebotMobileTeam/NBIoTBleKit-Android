package com.segwaydiscovery.bledemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.segwaydiscovery.bledemo.R;

public class SendCommandDialog extends Dialog implements OnClickListener {

    TextView tvCommandHelp;
    TextView tvCommandLognm;
    TextView tvCommandLognmEcu;
    TextView tvCommandWm;
    TextView tvCommandLogAll;
    private Context context;
    private EditText editText;
    private Button btnDc;
    private Button btnFb;
    private SendCommandDialogClickListener sendCommandDialogClickListener;

    public SendCommandDialog(@NonNull Context context) {
        super(context, R.style.commonDialogStyle);
        this.context = context;
    }

    public SendCommandDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected SendCommandDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public static SendCommandDialog create(Context context) {
        SendCommandDialog sendCommandDialog = new SendCommandDialog(context);
        return sendCommandDialog;
    }


    public SendCommandDialog setOnSendCommandDialogClickListener(SendCommandDialogClickListener sendCommandDialogClickListener) {
        this.sendCommandDialogClickListener = sendCommandDialogClickListener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLayout();
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.gravity = Gravity.BOTTOM;
        getWindow().setWindowAnimations(R.style.commonDialogStyle);
        lp.width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        lp.verticalMargin = 0.0f;
        lp.horizontalMargin = 0.0f;
        getWindow().setBackgroundDrawable(null);
        getWindow().setAttributes(lp);
    }

    private void showLayout() {
        View layout = LayoutInflater.from(context).inflate(R.layout.dialog_send_command, null);
        setContentView(layout);

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        editText = layout.findViewById(R.id.et_command);
        btnDc = layout.findViewById(R.id.btn_left);
        btnFb = layout.findViewById(R.id.btn_right);
        btnDc.setOnClickListener(this);
        btnFb.setOnClickListener(this);

        tvCommandHelp = layout.findViewById(R.id.tv_command_help);
        tvCommandLognm = layout.findViewById(R.id.tv_command_lognm);
        tvCommandLognmEcu = layout.findViewById(R.id.tv_command_lognm_ecu);
        tvCommandWm = layout.findViewById(R.id.tv_command_wm);
        tvCommandLogAll = layout.findViewById(R.id.tv_command_log_all);
        tvCommandHelp.setOnClickListener(this);
        tvCommandLognm.setOnClickListener(this);
        tvCommandLognmEcu.setOnClickListener(this);
        tvCommandWm.setOnClickListener(this);
        tvCommandLogAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (sendCommandDialogClickListener == null) {
            dismiss();
            return;
        }

        switch (v.getId()) {
            case R.id.btn_left:
                if (editText == null || TextUtils.isEmpty(editText.getText().toString())) {
                    return;
                }
                sendCommandDialogClickListener.onSendCommandByDcClick(editText.getText().toString());
                break;
            case R.id.btn_right:
                if (editText == null || TextUtils.isEmpty(editText.getText().toString())) {
                    return;
                }
                sendCommandDialogClickListener.onSendCommandByFbClick(editText.getText().toString());
                break;
            case R.id.tv_command_help:
                sendCommandDialogClickListener.onSendCommandByDcClick("help");
                break;
            case R.id.tv_command_lognm:
                sendCommandDialogClickListener.onSendCommandByDcClick("log -n modem");
                break;
            case R.id.tv_command_lognm_ecu:
                sendCommandDialogClickListener.onSendCommandByDcClick("log -n modem,ecu");
                break;
            case R.id.tv_command_wm:
                sendCommandDialogClickListener.onSendCommandByDcClick("wm");
                break;
            case R.id.tv_command_log_all:
                sendCommandDialogClickListener.onSendCommandByDcClick("log -f all");
                break;
        }

        dismiss();
    }
}
