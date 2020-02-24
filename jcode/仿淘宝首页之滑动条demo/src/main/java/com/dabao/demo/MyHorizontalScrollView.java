package com.dabao.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by zzt on 2018/10/30.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {
    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *
     * @param l  本次向左移动超出（原点）0.0的距离
     * @param t
     * @param oldl  前一次向左移动超出（原点）0.0的距离
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        myHorizontalScrollView_JieKou.onScrollChanged_JieKou( l,  t,  oldl,  oldt);
    }

    public interface MyHorizontalScrollView_JieKou{
        void onScrollChanged_JieKou(int l, int t, int oldl, int oldt);
    }

    MyHorizontalScrollView_JieKou myHorizontalScrollView_JieKou;

    public void setMyHorizontalScrollView_JieKou(MyHorizontalScrollView_JieKou myHorizontalScrollView_JieKou) {
        this.myHorizontalScrollView_JieKou = myHorizontalScrollView_JieKou;
    }
}
