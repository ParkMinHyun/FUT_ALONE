package com.example.fut.common.base;

import android.support.annotation.NonNull;
import android.view.View;

public abstract class EventDelegateBindHolder<T, D extends EventDelegate> extends BaseBindHolder<T> {

    private final D delegate;

    public EventDelegateBindHolder(View itemView, @NonNull D delegate) {
        super(itemView);
        this.delegate = delegate;
    }

    public EventDelegateBindHolder(View itemView) {
        super(itemView);
        this.delegate = null;
    }

    protected final D getEventDelegate() {
        return delegate;
    }
}
