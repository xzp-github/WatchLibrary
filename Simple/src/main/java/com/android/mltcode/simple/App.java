package com.android.mltcode.simple;

import android.app.Application;

import com.android.mltcode.watchlib.WatchUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WatchUtils.init(this);
    }
}
