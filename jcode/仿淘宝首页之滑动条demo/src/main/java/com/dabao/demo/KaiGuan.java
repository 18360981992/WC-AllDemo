package com.dabao.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


/**
 * Created by Administrator on 2017/12/1/001.
 */

public class KaiGuan extends View {

    private final Paint paint;
    public int measuredHeight;
    public int measuredWidth;  //当前控件的宽度
    public float yd_measuredWidth=0;  // 设置移动器的默认宽度
    Context context;
    float yd_zhi=0.0f;  // 设置移动器的默认移动值
    public int width;
    public int num=0;  // gridview的列数

    int listsize=0;  // 数据源的长度
    int itemWidth=0;  // item条目的宽度

    String bg_Color="#33000000";  // 滑动条的背景色
    String yd_Color="#777777";  // 滑动条的移动器颜色

    public KaiGuan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        paint = new Paint();

        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;  // 获取屏幕宽度
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //当前控件的宽度
        measuredWidth = getMeasuredWidth();
        //当前控件的高度
        measuredHeight = getMeasuredHeight();

        // 设置默认的移动器的宽度
        if(yd_measuredWidth==0){
            yd_measuredWidth = measuredWidth / 2;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(1);
        paint.setColor(Color.parseColor(bg_Color));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        //最左边绿色半圆  参数1，确认半圆的大小，，参数2,绘制的起始角度，，参数3，是绘制的总和角度，，参数4，是否包含圆心，参数5，画笔
        //参数2和参数3，一定要遵循android的y轴的特殊性，以及参数3的正/逆时针的旋转
        canvas.drawArc(new RectF(0,0,measuredHeight,measuredHeight),90,180,true,paint);
//        //绘制中心的方块
        canvas.drawRect(measuredHeight/2.0f,0,measuredWidth-measuredHeight/2.0f,measuredHeight,paint);
//        //绘制最右边的半圆
        canvas.drawArc(new RectF(measuredWidth-measuredHeight,0,measuredWidth,measuredHeight),90,-180,true,paint);

        // 绘制移动器
        paint.setColor(Color.parseColor(yd_Color));

        canvas.drawArc(new RectF(0+yd_zhi,0,measuredHeight+yd_zhi,measuredHeight),90,180,true,paint);
//        //绘制中心的方块
        canvas.drawRect(measuredHeight/2.0f+yd_zhi,0,yd_measuredWidth-measuredHeight/2.0f+yd_zhi,measuredHeight,paint);
//        //绘制最右边的半圆
        canvas.drawArc(new RectF(yd_measuredWidth-measuredHeight+yd_zhi,0,yd_measuredWidth+yd_zhi,measuredHeight),90,-180,true,paint);

    }

    // 设置移动值 并进行重绘
    public void setYd_zhi(float yd_zhi) {
        this.yd_zhi = yd_zhi;
        postInvalidate();
    }

    // 初始化
    public void setInit(int listsize,int itemWidth){
        this.listsize=listsize;
        this.itemWidth=itemWidth;

        num = Math.round(listsize / 2.0f);  // 计算得出gridview的列数

        float i = (width+0.0f) / itemWidth; // 屏幕宽度/条目宽度得到具体条目个数  用来计算比列
        Log.i("jba","i=="+i);
        // 得到移动器的宽度
        yd_measuredWidth = measuredWidth * (i / num);  // 根据所显示的比列来计算移动器的宽度
        postInvalidate();
    }

    public float getkg_MAX(){
        float kg_MAX = measuredWidth - yd_measuredWidth;  // 获取开关最大可移动的距离
        return kg_MAX;
    }

    public int getmyHorizontalScrollView_MAX(){
        int myHorizontalScrollView_MAX = itemWidth * num-width;  // 获取HorizontalScrollView可滑动的最大值
        return myHorizontalScrollView_MAX;
    }

    // 设置背景色
    public void setBg_Color(String bg_Color) {
        if(!TextUtils.isEmpty(bg_Color)){
            this.bg_Color = bg_Color;
        }
        postInvalidate();
    }

    // 设置移动器颜色
    public void setYd_Color(String yd_Color) {
        if(!TextUtils.isEmpty(yd_Color)){
            this.yd_Color = yd_Color;
        }
        postInvalidate();
    }
}
