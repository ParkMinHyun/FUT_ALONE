package com.example.fut.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.fut.common.enums.AnimationDirection;
import com.example.fut.common.util.FLog;
import butterknife.ButterKnife;

public abstract class BaseActivity<C extends BaseController, V extends BaseActivityViews> extends AppCompatActivity {

    protected C controller = createController();
    protected V views = createViews();

    @NonNull
    protected abstract C createController();

    @NonNull
    protected abstract V createViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViewsAndController();
        initViews();
        initEnterAnimation();
        initController();

        //AceUtils.site(getClass().getSimpleName()); //Activity의 이름을 site 이름으로 남김.
        FLog.d(getClass(), getClass().getSimpleName() + ": onCreate()");
    }

    private void initializeViewsAndController() {
        if (views instanceof EventDelegateViews) {
            ((EventDelegateViews) views).setDelegate((EventDelegate) controller);
        }
        controller.setViews(views);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getController().onDestroy();
        getViews().onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getController().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getController().onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getController().onStart();
    }

    @Override
    protected void onStop() {
        getController().onStop();
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getController().onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
        if (getViews().onBackPressed() || getController().onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    private void initViews() {
        setContentView(getLayoutResId());
        FrameLayout rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        getViews().setRootView(rootView);
        getViews().setActivity(this);
        ButterKnife.bind(getViews(), rootView);
        getViews().init(this);
        getViews().initAdditionalFunction(this);
    }

    /**
     * getViews 자체는 null이 아니지만, 내부적으로 destroy 가능성이 있기 때문에
     * Async 후처리를 할때는 isDestroyed() 함수로 체크해주는 것이 좋다.
     */
    @NonNull
    private V getViews() {
        return views;
    }

    private void initController() {
        getController().setActivity(this);
        getController().setViews(getViews());
        getController().onCreate();
        getController().initAfterViewInflated();
    }

    @NonNull
    public final C getController() {
        return controller;
    }

    @LayoutRes
    public abstract int getLayoutResId();

    @Override
    public void finish() {
        getController().onFinish();
        super.finish();
        initFinishAnimation();
    }

    protected AnimationDirection getAnimationDirection() {
        return AnimationDirection.LEFT;
    }

    protected void initEnterAnimation() {
//        overridePendingTransition(ScreenAnimationUtils.getEnterAnimationId(getAnimationDirection()), ScreenAnimationUtils.getFinishAnimationId(getAnimationDirection()));
    }

    protected void initFinishAnimation() {
//        overridePendingTransition(ScreenAnimationUtils.getEnterAnimationId(getAnimationDirection()), ScreenAnimationUtils.getFinishAnimationId(getAnimationDirection()));
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
