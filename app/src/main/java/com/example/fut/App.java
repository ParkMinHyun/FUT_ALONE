package com.example.fut;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App instance;
    private static Activity currentActivity;
    private static final String APP_NAME = "FUT";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initLibraries();
    }

    private void initLibraries() {
    }

    public static App getInstance() {
        return instance;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }
}
