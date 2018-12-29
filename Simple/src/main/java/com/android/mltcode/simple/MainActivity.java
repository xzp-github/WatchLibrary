package com.android.mltcode.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mltcode.watchlib.config.Logger;
import com.android.mltcode.watchlib.view.LeftRightButton;


public class MainActivity extends Activity implements LeftRightButton.LeftRightListener {
    private final String TAG = "MainActivity";

    private LeftRightButton mLeftRightButton;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_text);

        mLeftRightButton = findViewById(R.id.leftrightbutton);
        mLeftRightButton.setLeftRightListener(this);
    }

    public void onAction(View view) {
        Logger.d(TAG, "log-------------------------");
    }



    @Override
    public void onLeftClick(View view) {
        if(mTextView != null){
            mTextView.setText("onLeftClick");
        }
    }

    @Override
    public void onRightClick(View view) {
        if(mTextView != null){
            mTextView.setText("onRightClick");
        }
    }
}
