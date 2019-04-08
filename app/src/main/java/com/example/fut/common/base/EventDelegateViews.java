package com.example.fut.common.base;

public abstract class EventDelegateViews<D extends EventDelegate> extends BaseActivityViews {

    @Override
    protected abstract void init(BaseActivity context);

    private D delegate;

    public EventDelegateViews() {
        super();
    }

    public void setDelegate(D delegate) {
        this.delegate = delegate;
    }

    public D getEventDelegate() {
        return delegate;
    }
}
