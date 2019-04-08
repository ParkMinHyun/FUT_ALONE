package com.example.fut.common.base;

import android.content.Intent;
import android.support.annotation.Nullable;

public abstract class BaseController<V extends BaseActivityViews> {

    @Nullable
    protected BaseActivity activity;

    LifeCycleHelper lifeCycleHelper;
    protected V views;

    protected void onCreate() {
        initLifeCycleHelperOnCreate();
    }


    protected boolean isUsingEventBus() {
        return false;
    }

    public void onDestroy() {
        activity = null;
        if (lifeCycleHelper != null) {
            lifeCycleHelper.onDestroy();
            lifeCycleHelper = null;
        }
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onResume() {
        if (lifeCycleHelper != null) {
            lifeCycleHelper.onResume();
        }
    }

    public void onPause() {
        if (lifeCycleHelper != null) {
            lifeCycleHelper.onPause();
        }
    }

    protected boolean isPaused() {
        return lifeCycleHelper != null && lifeCycleHelper.isPaused();

    }

    protected void onFinish() {
    }

    /**
     * View가 생성되고 난 뒤에 작업을 초기화 작업을 진행하는 메서드.
     * View가 생성되기 전에 해야하는 작업은 onCreate, onCreateView 등을 오버라이딩 해서 사용
     *
     * BaseActivty onCreate() 마지막 라인에서 실행됨
     * BaseFragment onCreateView() 마지막 라인에서 실행됨
     */
    public abstract void initAfterViewInflated();

    public void setActivity(@Nullable BaseActivity activity) {
        this.activity = activity;
    }

    @Nullable
    public BaseActivity getActivity() {
        return activity;
    }

    public boolean isActivityFinishing() {
        return activity == null || activity.isFinishing();
    }

    protected boolean isActivityValid() {
        return !isActivityFinishing();
    }

    public boolean onBackPressed() {
        return false;
    }

    public void finishActivity() {
        if (isActivityFinishing()) {
            return;
        }
        if (activity != null) {
            activity.finish();
        }
    }

    private void initLifeCycleHelperOnCreate() {
        if (lifeCycleHelper != null) {
            return;
        }
        lifeCycleHelper = new LifeCycleHelper();
        lifeCycleHelper.onCreate();
    }

    public LifeCycleHelper getLifeCycleHelper() {
        return lifeCycleHelper;
    }

    @Nullable
    public V getViews() {
        return views;
    }

    public void setViews(V views) {
        this.views = views;
    }

    protected void onNewIntent(Intent intent) {

    }
}
