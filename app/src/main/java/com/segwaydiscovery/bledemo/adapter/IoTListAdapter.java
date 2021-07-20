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
import com.segwaydiscovery.bledemo.enumation.IoTTypeEnum;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description 设备列表适配器
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/3/2 2:53 PM
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

        holder.tvDeviceType.setText(ioT.getIoTTypeEnum().getName());
        holder.tvDeviceType.setVisibility(ioT.getIoTTypeEnum().getCode() == 0 ? View.GONE : View.VISIBLE);
        holder.tvDeviceType.setBackgroundResource(getBackgroundResource(ioT.getIoTTypeEnum().getCode()));
    }

    private int getBackgroundResource(int type) {
        if (type == IoTTypeEnum.IOT_TYPE_ONMI.getCode()) {
            return R.drawable.shape_gradient_blue;
        }

        if (type == IoTTypeEnum.IOT_TYPE_YIWEI.getCode()) {
            return R.drawable.shape_gradient_orange;
        }

        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_device_name)
        TextView tvDeviceName;

        @BindView(R.id.tv_device_mac)
        TextView tvDeviceMac;

        @BindView(R.id.tv_device_type)
        TextView tvDeviceType;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

}
