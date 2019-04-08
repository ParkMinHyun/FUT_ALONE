package com.example.fut.common.base;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fut.App;

/**
 * Activty, Fragment등에서 뷰 객체와 로직을 분리하기 위한 클래스.
 *
 * BaseActivty, BaseFragment에서 직접 기본생성자를 이용하여 객체를 만들어 세팅하기 때문에
 * 꼭 기본생성자가 필요하다.
 *
 * @author Nam
 * @since 16. 2. 21..
 */
abstract class BaseViews<C extends Context> {
    private ViewGroup mRootView;
    protected boolean hasInitData;

    /**
     * context 되도록 init에서만 사용하고, 필드로 들고 있지 않도록 하는것이 좋습니다.
     * context를 별도의 필드로 들게 되는 경우 onDestroy 호출을 사용해주세요.
     */
    protected abstract void init(C context);

    /**
     * views가 context나 그 외의 메모리에서 들고 있으면 안되는 객체들에 null을 할당해주도록 한다.
     */
    protected void onDestroy() {
        mRootView = null;
    }

    public boolean isDestroyed() {
        return mRootView == null;
    }

    public void setRootView(ViewGroup mRootView) {
        this.mRootView = mRootView;
    }

    public ViewGroup getRootView() {
        return mRootView;
    }

    @Nullable
    public Context getContext() {
        if (mRootView == null) {
            return null;
        }

        return mRootView.getContext();
    }

    public <T extends View> T findViewById(int id) {
        return (T) mRootView.findViewById(id);
    }

    public void post(Runnable action) {
        postDelayed(action, 0);
    }

    public void postDelayed(Runnable action, long duration) {
        if (isDestroyed()) {
            return;
        }

        if (duration == 0) {
            mRootView.post(action);
        } else {
            mRootView.postDelayed(action, duration);
        }
    }

    protected void removeCallbacks(Runnable action) {
        if (isDestroyed()) {
            return;
        }
        mRootView.removeCallbacks(action);
    }

    protected int getPixel(int dimenId) {
        return mRootView.getResources().getDimensionPixelSize(dimenId);
    }

    public void showToast(int stringResId) {
        showToast(mRootView.getContext().getString(stringResId));
    }

    public void showToast(String text) {
        Toast.makeText(mRootView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public boolean onBackPressed() {
        return false;
    }

    protected String getString(@StringRes int resId) {
        return App.getInstance().getString(resId);
    }

    protected String getString(@StringRes int resId, Object... formatArgs) {
        return App.getInstance().getString(resId, formatArgs);
    }

    protected int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(App.getInstance(), resId);
    }

    protected void initAdditionalFunction(BaseActivity activity) {
    }

    protected void onRootViewSliding(float posX, boolean isStarted) {
    }

    public boolean hasInitData() {
        return hasInitData;
    }

    public void setHasInitData(boolean hasInitData) {
        this.hasInitData = hasInitData;
    }
}
