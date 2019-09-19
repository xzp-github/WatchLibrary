package com.android.mltcode.watchlib.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mltcode.watchlib.R;


/**
 * @author zhiwei_zhu
 */
public class LeftRightButton extends LinearLayout implements View.OnClickListener {

    /**
     * 默认图片模式
     */
    private final int BUTTON_TYPE_IMG = 1;

    private Context mContext;
    private Resources mRes;
    private LinearLayout mLayoutLeft, mLayoutRight;

    private ImageView mLeftImg, mRightImg;

    private TextView mLeftTv, mRightTv;


    private LeftRightListener mLeftRightListener;

    private Drawable mLeftDrawableBg;

    private Drawable mRightDrawableBg;

    private Drawable mLeftDrawableImg, mRightDrawableImg;

    private String mLeftText, mRightText;

    private int mType = BUTTON_TYPE_IMG;


    public LeftRightButton(Context context) {
        this(context, null);
    }

    public LeftRightButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftRightButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mRes = getResources();
        LayoutInflater.from(context).inflate(R.layout.left_right_layout, this, true);;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LeftRightView);
        mLeftDrawableBg = typedArray.getDrawable(R.styleable.LeftRightView_leftBackground);
        mRightDrawableBg = typedArray.getDrawable(R.styleable.LeftRightView_rightBackground);
        mType = typedArray.getInt(R.styleable.LeftRightView_leftRightType, BUTTON_TYPE_IMG);
        mLeftDrawableImg = typedArray.getDrawable(R.styleable.LeftRightView_leftImgSrc);
        mRightDrawableImg = typedArray.getDrawable(R.styleable.LeftRightView_rightImgSrc);
        mLeftText = typedArray.getString(R.styleable.LeftRightView_leftText);
        mRightText = typedArray.getString(R.styleable.LeftRightView_rightText);
        typedArray.recycle();

        mLayoutLeft = (LinearLayout) findViewById(R.id.lr_cancel);
        mLayoutRight = (LinearLayout) findViewById(R.id.lr_confrim);

        mLayoutLeft.setBackground(mLeftDrawableBg);
        mLayoutRight.setBackground(mRightDrawableBg);

        mLeftImg = (ImageView) findViewById(R.id.lr_left_img);
        mRightImg = (ImageView) findViewById(R.id.lr_right_img);

        mLeftTv = (TextView) findViewById(R.id.lr_left_tv);
        mRightTv = (TextView) findViewById(R.id.lr_right_tv);

        if(mType == BUTTON_TYPE_IMG){
            mLeftTv.setVisibility(GONE);
            mRightTv.setVisibility(GONE);
            mLeftImg.setVisibility(VISIBLE);
            mRightImg.setVisibility(VISIBLE);

            mLeftImg.setImageDrawable(mLeftDrawableImg);
            mRightImg.setImageDrawable(mRightDrawableImg);

        }else {
            mLeftTv.setVisibility(VISIBLE);
            mRightTv.setVisibility(VISIBLE);
            mLeftImg.setVisibility(GONE);
            mRightImg.setVisibility(GONE);

            mLeftTv.setText(mLeftText);
            mRightTv.setText(mRightText);
        }

        mLayoutLeft.setOnClickListener(this);
        mLayoutRight.setOnClickListener(this);
    }

    /**
     * 设置监听
     * @param listener
     */
    public void setLeftRightListener(LeftRightListener listener){
        if(listener != null){
            mLeftRightListener = listener;
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.lr_cancel) {
            if (mLeftRightListener != null) {
                mLeftRightListener.onLeftClick(v);
            }

        } else if (i == R.id.lr_confrim) {
            if (mLeftRightListener != null) {
                mLeftRightListener.onRightClick(v);
            }
        }
    }


    public interface LeftRightListener{
        void onLeftClick(View view);
        void onRightClick(View view);
    }
}
