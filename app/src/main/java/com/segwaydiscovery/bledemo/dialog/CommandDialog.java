package com.segwaydiscovery.bledemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.segwaydiscovery.bledemo.Constants;
import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.adapter.BaseAdapter;
import com.segwaydiscovery.bledemo.adapter.CommandAdapter;
import com.segwaydiscovery.bledemo.bean.Command;
import com.segwaydiscovery.bledemo.enumation.CommandEnum;
import com.segwaydiscovery.bledemo.enumation.HelmetCommandEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/5/27 2:31 PM
 */
public class CommandDialog extends Dialog {

    private Context context;
    private RecyclerView rvCommands;
    private CommandAdapter commandAdapter;
    private CommandDialogClickListener commandDialogClickListener;
    private int deviceType;

    public CommandDialog(@NonNull Context context) {
        super(context, R.style.commonDialogStyle);
        this.context = context;
    }

    public CommandDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected CommandDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public static CommandDialog create(Context context) {
        CommandDialog commandDialog = new CommandDialog(context);
        return commandDialog;
    }

    public CommandDialog setDeviceType(int deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public CommandDialog setOnCommandDialogClickListener(CommandDialogClickListener commandDialogClickListener) {
        this.commandDialogClickListener = commandDialogClickListener;
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
        View layout = LayoutInflater.from(context).inflate(R.layout.dialog_commands, null);
        setContentView(layout);

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        rvCommands = layout.findViewById(R.id.rv_commands);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        rvCommands.setLayoutManager(layoutManager);
        commandAdapter = new CommandAdapter();
        commandAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                commandDialogClickListener.onClick(commandAdapter.getItem(position));
                dismiss();
            }
        });
        rvCommands.setAdapter(commandAdapter);
        createCommandList();
    }

    private void createCommandList() {
        List<Command> commandList = new ArrayList<>();

        if (deviceType == 1) {
            for (CommandEnum commandEnum : CommandEnum.values()) {
                commandList.add(new Command(commandEnum.getCommand(), commandEnum.getDesc()));
            }
        } else if (deviceType == 2) {
            for (HelmetCommandEnum helmetCommandEnum : HelmetCommandEnum.values()) {
                commandList.add(new Command(helmetCommandEnum.getCommand(), helmetCommandEnum.getDesc()));
            }
        }

        commandAdapter.setItems(commandList);
    }
}