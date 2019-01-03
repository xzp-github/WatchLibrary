package com.android.mltcode.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mltcode.watchlib.config.Logger;
import com.android.mltcode.watchlib.view.CircleProgressBar;
import com.android.mltcode.watchlib.view.LeftRightButton;

import java.util.Random;


public class MainActivity extends Activity implements LeftRightButton.LeftRightListener {
    private final String TAG = "MainActivity";

    private LeftRightButton mLeftRightButton;
    private TextView mTextView;

    private CircleProgressBar mCalorieCpb;
    private CircleProgressBar mDurationCpb;
    private CircleProgressBar mStandCpb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_text);

        mLeftRightButton = findViewById(R.id.leftrightbutton);
        mLeftRightButton.setLeftRightListener(this);

        mCalorieCpb = (CircleProgressBar) findViewById(R.id.calorie_cpb);
        mDurationCpb = (CircleProgressBar) findViewById(R.id.duration_cpb);
        mStandCpb = (CircleProgressBar) findViewById(R.id.stand_cpb);

        setCircleValue();
    }

    public void onAction(View view) {
        Logger.d(TAG, "log-------------------------");
    }



    @Override
    public void onLeftClick(View view) {
        if(mTextView != null){
            mTextView.setText("onLeftClick");
        }
        setCircleValue();
    }

    @Override
    public void onRightClick(View view) {
        if(mTextView != null){
            mTextView.setText("onRightClick");
        }

        setCircleValue();
    }

    private void setCircleValue() {
        if(mCalorieCpb != null){
            mCalorieCpb.setValue((float) (Math.random()*100f));
        }

        if(mDurationCpb != null){
            mDurationCpb.setValue((float) (Math.random()*100f));
        }

        if(mStandCpb != null){
            mStandCpb.setValue((float) (Math.random()*100f));
        }
    }

}
