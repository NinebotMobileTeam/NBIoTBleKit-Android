package com.segwaydiscovery.bledemo.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.bean.IoT;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description 设备列表适配器
 *
 */
public class IoTListAdapter extends BaseAdapter<IoT, IoTListAdapter.ViewHolder> {

    private Context context;

    public IoTListAdapter(Context context) {
        super(ViewHolder.class, R.layout.item_device);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        IoT ioT = getItem(position);
        holder.tvDeviceName.setText(ioT.getName());
        holder.tvDeviceMac.setText(ioT.getMac());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_device_name)
        TextView tvDeviceName;

        @BindView(R.id.tv_device_mac)
        TextView tvDeviceMac;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

}
