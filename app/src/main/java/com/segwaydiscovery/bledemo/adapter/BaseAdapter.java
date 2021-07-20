package com.segwaydiscovery.bledemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Class<VH> vhClazz;

    private int itemLayoutResId;

    private Map<Integer, Class<VH>> viewHolderMap;

    private Map<Integer, Integer> itemLayoutResIdMap;

    private List<T> items = new ArrayList<T>();

    private OnItemClickListener onItemClickListener;

    private OnItemClickListener2 onItemClickListener2;

    public BaseAdapter(Class<VH> vhClazz, int itemLayoutResId) {
        this.vhClazz = vhClazz;
        this.itemLayoutResId = itemLayoutResId;
    }

    public BaseAdapter(Map<Integer, Class<VH>> viewHolderMap, Map<Integer, Integer> itemLayoutResIdMap) {
        this.viewHolderMap = viewHolderMap;
        this.itemLayoutResIdMap = itemLayoutResIdMap;
    }

    @Override
    public int getItemViewType(int position) {
        if (itemLayoutResIdMap != null) {
            return itemLayoutResIdMap.get(position);
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        final int itemLayoutResId;
        if (viewHolderMap != null) {
            itemLayoutResId = itemLayoutResIdMap.get(viewType);
        } else {
            itemLayoutResId = this.itemLayoutResId;
        }

        final Class<VH> vhClazz;
        if (viewHolderMap != null) {
            vhClazz = viewHolderMap.get(viewType);
        } else {
            vhClazz = this.vhClazz;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayoutResId, parent, false);
        try {
            VH viewHolder = vhClazz.getConstructor(View.class).newInstance(view);
            ButterKnife.bind(viewHolder, view);
            return viewHolder;
        } catch (Exception e) {

        }
        return null;
    }

    public void onBindViewHolder(final VH holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = new ArrayList<T>(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void removeItem(int pos) {
        int index = 0;
        Iterator<T> it = this.items.iterator();
        while (it.hasNext()) {
            T t = it.next();
            if (pos == index) {
                it.remove();
            }
            index++;
        }
        notifyItemRemoved(pos);
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItemsDx(List<T> items) {
        this.items.addAll(0, items);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemClickListener2(OnItemClickListener2 onItemClickListener2) {
        this.onItemClickListener2 = onItemClickListener2;
    }

    protected void onItemClick(int position) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(position);
        }
    }

    protected void onItemClick2(int position, View v) {
        if (onItemClickListener2 != null) {
            onItemClickListener2.onItemClick(position, v);
        }
    }

    protected int getSize() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemClickListener2 {
        void onItemClick(int position, View v);
    }
}

