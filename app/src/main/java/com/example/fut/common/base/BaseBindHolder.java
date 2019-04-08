package com.example.fut.common.base;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseBindHolder<T> extends RecyclerView.ViewHolder {

    private final Resources resources;

    public BaseBindHolder(View itemView) {
        super(itemView);
        resources = itemView.getResources();
        ButterKnife.bind(this, itemView);
    }

    protected abstract int getItemLayoutResId();

    protected Resources getResources() {
        return resources;
    }

    public abstract void bindView(T item, int position);

    public void performActionForLastItem(boolean isLastItem) {
    }

    public void bindViewWithPayload(T item, int position, List<Object> payloads) {
    }
}

