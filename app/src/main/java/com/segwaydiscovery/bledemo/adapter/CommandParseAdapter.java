package com.segwaydiscovery.bledemo.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.segwaydiscovery.bledemo.R;
import com.segwaydiscovery.bledemo.bean.CommandParse;


/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/5/25 10:17 AM
 */
public class CommandParseAdapter extends BaseAdapter<CommandParse, CommandParseAdapter.ViewHolder> {

    public CommandParseAdapter() {
        super(ViewHolder.class, R.layout.item_data_parse);
    }

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        CommandParse commandParse = getItem(position);
        holder.tvIndex.setText(commandParse.getIndex());
        holder.tvParameter.setText(commandParse.getParameter());
        holder.tvDescription.setText(commandParse.getDescription());
        holder.tvValue.setText(commandParse.getValue());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvIndex;
        TextView tvParameter;
        TextView tvDescription;
        TextView tvValue;

        public ViewHolder(View view) {
            super(view);
            tvIndex = view.findViewById(R.id.tv_index);
            tvParameter = view.findViewById(R.id.tv_parameter);
            tvDescription = view.findViewById(R.id.tv_description);
            tvValue = view.findViewById(R.id.tv_value);
        }

    }

}
