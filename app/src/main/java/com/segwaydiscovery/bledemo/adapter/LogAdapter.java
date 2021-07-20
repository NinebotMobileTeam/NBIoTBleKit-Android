package com.segwaydiscovery.bledemo.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sd.blecontrol.interfaces.LogType;
import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.bean.IoTLog;
import com.segwaydiscovery.bledemo.enumation.LogEnum;
import com.segwaydiscovery.bledemo.util.LogUtil;

import java.text.SimpleDateFormat;


/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/1/27 11:20 AM
 */
public class LogAdapter extends BaseAdapter<IoTLog, LogAdapter.ViewHolder> {

    private Context context;

    private CommandParseAdapter commandParseAdapter;

    public LogAdapter(Context context) {
        super(ViewHolder.class, R.layout.item_log);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        IoTLog ioTLog = getItem(position);

        if (ioTLog.getType() == LogEnum.LOG_TYPE_DESCRIPTION.getType()) {
            holder.tvLog.setVisibility(View.GONE);
            holder.llCommandParse.setVisibility(View.VISIBLE);

            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
                    false);
            holder.rvCommandParse.setLayoutManager(layoutManager);
            commandParseAdapter = new CommandParseAdapter();
            holder.rvCommandParse.setAdapter(commandParseAdapter);
            commandParseAdapter.setItems(ioTLog.getCommandParseList());

        } else {
            holder.tvLog.setVisibility(View.VISIBLE);
            holder.llCommandParse.setVisibility(View.GONE);

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[HH:mm:ss] ");
            holder.tvLog.setText(simpleDateFormat.format(ioTLog.getDate()) + LogEnum.getEnum(ioTLog.getType()).getTag() + " " + (ioTLog.getType() == LogType.LOG_TYPE_APP_TO_IOT || ioTLog.getType() == LogType.LOG_TYPE_IOT_TO_APP ? LogUtil.format16(getItem(position).getContent()) : getItem(position).getContent()));
            holder.tvLog.setTextColor(context.getColor(LogEnum.getEnum(ioTLog.getType()).getTextColor()));
        }


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLog;

        LinearLayout llCommandParse;

        RecyclerView rvCommandParse;

        public ViewHolder(View view) {
            super(view);
            tvLog = view.findViewById(R.id.tv_log);
            llCommandParse = view.findViewById(R.id.ll_description);
            rvCommandParse = view.findViewById(R.id.rv_command_parse);
        }

    }

}
