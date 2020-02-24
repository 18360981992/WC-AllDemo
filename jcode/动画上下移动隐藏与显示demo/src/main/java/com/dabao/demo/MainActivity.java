package com.dabao.demo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mHiddenLayout;

    private float mDensity;

    private int mHiddenViewMeasuredHeight;

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mHiddenLayout = (LinearLayout) findViewById(R.id.linear_hidden);
        final LinearLayout mHiddenLayout1 = (LinearLayout) findViewById(R.id.linear_hidden1);
        mIv = (ImageView) findViewById(R.id.my_iv);

        //通过设置监听来获取 微弹窗 控件的高度
        mHiddenLayout1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mHiddenLayout1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                mHiddenViewMeasuredHeight = mHiddenLayout1.getMeasuredHeight();

                Log.i("jba","mHiddenViewMeasuredHeight==="+mHiddenViewMeasuredHeight);
            }
        });

//        mDensity = getResources().getDisplayMetrics().density;
//
//        mHiddenViewMeasuredHeight = (int) (mDensity * 480 + 0.5);
    }

    public void onClick(View v) {
        if (mHiddenLayout.getVisibility() == View.GONE) {
            animateOpen(mHiddenLayout);
            animationIvOpen();
        } else {
            animateClose(mHiddenLayout);
            animationIvClose();
        }
    }

    private void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

    }

    private void animationIvOpen() {
        RotateAnimation animation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        mIv.startAnimation(animation);
    }

    private void animationIvClose() {
        RotateAnimation animation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        mIv.startAnimation(animation);
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
//        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }
}
