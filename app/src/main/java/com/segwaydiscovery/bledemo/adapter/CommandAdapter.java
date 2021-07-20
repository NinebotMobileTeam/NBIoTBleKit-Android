package com.segwaydiscovery.bledemo.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.bean.Command;

/**
 * description CommandAdapter
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/4/14 5:36 PM
 */
public class CommandAdapter extends BaseAdapter<Command, CommandAdapter.ViewHolder> {

    public CommandAdapter() {
        super(ViewHolder.class, R.layout.item_command);
    }

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Command command = getItem(position);
        holder.tvCommand.setText(command.getDesc());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCommand;

        public ViewHolder(View view) {
            super(view);
            tvCommand = view.findViewById(R.id.tv_command_desc);
        }

    }

}
