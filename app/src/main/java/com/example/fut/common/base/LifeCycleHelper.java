
package com.example.fut.common.base;

import com.example.fut.common.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class LifeCycleHelper {
    protected LifeCycleState lifeCycleState;


    protected List<LifeCycleListener> lifeCycleListeners;

    public void onPause() {
        lifeCycleState = LifeCycleState.PAUSED;
    }

    public void onResume() {
        lifeCycleState = LifeCycleState.RESUMED;
    }

    public void onCreate() {
        lifeCycleState = LifeCycleState.VIEW_CREATED;
        if (CollectionUtils.isEmpty(lifeCycleListeners)) {
            return;
        }

        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onCreate();
        }
    }

    public void onDestroy() {
        lifeCycleState = LifeCycleState.DESTROYED;

        if (CollectionUtils.isEmpty(lifeCycleListeners)) {
            return;
        }

        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.onDestroy();
        }

        lifeCycleListeners.clear();
        lifeCycleListeners = null;
    }

    public void addLifeCycleListener(LifeCycleListener lifeCycleListener) {
        if (lifeCycleListeners == null) {
            lifeCycleListeners = new ArrayList<>();
        }
        lifeCycleListeners.add(lifeCycleListener);
    }

    public boolean isPaused() {
        return lifeCycleState == LifeCycleState.PAUSED;
    }

    public boolean isDestroyed() {
        return lifeCycleState == LifeCycleState.DESTROYED;
    }
}
