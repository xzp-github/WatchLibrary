package com.android.mltcode.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.mltcode.watchlib.config.Logger;


public class MainActivity extends Activity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAction(View view) {
        Logger.d(TAG, "log-------------------------");
    }
}
