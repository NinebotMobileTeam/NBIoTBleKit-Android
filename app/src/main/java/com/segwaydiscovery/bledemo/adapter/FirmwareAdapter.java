package com.segwaydiscovery.bledemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.bean.FirmwareBean;

/**
 * description
 *
 * @author sen
 * @version 1.0
 * @since 2021-05-28
 */
public class FirmwareAdapter extends BaseAdapter<FirmwareBean, FirmwareAdapter.ViewHolder> {

    private FirmwareBean selectFirmwareBean;
    private Context context;

    public FirmwareAdapter(Context context) {
        super(ViewHolder.class, R.layout.item_firmware);
        this.context = context;
    }

    public void setSelectFirmwareBean(FirmwareBean selectFirmwareBean) {
        this.selectFirmwareBean = selectFirmwareBean;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        FirmwareBean firmwareBean = getItem(position);
        holder.tvFirmware.setText(firmwareBean.getName());

        if (null != selectFirmwareBean && selectFirmwareBean.getUrl().equals(firmwareBean.getUrl())) {
            holder.tvFirmware.setBackgroundColor(ContextCompat.getColor(context, R.color.color098));
        } else {
            holder.tvFirmware.setBackgroundColor(ContextCompat.getColor(context, R.color.colorFFF));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFirmware;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFirmware = itemView.findViewById(R.id.tv_firmware);
        }
    }
}
