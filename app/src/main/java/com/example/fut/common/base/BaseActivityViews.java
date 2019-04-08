package com.example.fut.common.base;

public abstract class BaseActivityViews extends BaseViews<BaseActivity> {

    private BaseActivity activity;

    @Override
    public void initAdditionalFunction(BaseActivity activity) {
        super.initAdditionalFunction(activity);
     }

    public BaseActivity getActivity() {
        return activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity = null;
    }

}
