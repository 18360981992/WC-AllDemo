package com.ifeng.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by zzt on 2018/8/23.
 */

public class MPChear extends View {

    public Paint waiyuan_bi;
    public Paint neiyuan_bi;
    public TextPaint zi_bi;
    public int width;
    public int height;
    public int zx_x;
    public int zx_y;
    public Path waiyuan_path;
    public Path neiyuan_path;
    public float wai_zuo_x;
    public float wai_zuo_y;
    public float wai_you_x;
    public float wai_you_y;
    public RectF rectF;

    public MPChear(Context context) {
        super(context);init();
    }

    public MPChear(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init();
    }

    public MPChear(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }

    public void init(){
        // 获取手机的屏幕宽和高
        WindowManager windowManager = (WindowManager) getContext().getSystemService (Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay ();
        width = defaultDisplay.getWidth();
        height = defaultDisplay.getHeight();
        zx_x = width / 2;
        zx_y = height / 2;

        wai_zuo_x = zx_x - 100f;
        wai_zuo_y = zx_y - 100f;
        wai_you_x = zx_x + 100f;
        wai_you_y = zx_y + 100f;
        rectF = new RectF(wai_zuo_x, wai_zuo_y, wai_you_x, wai_you_y);

        waiyuan_bi = new Paint(Paint.ANTI_ALIAS_FLAG);
        waiyuan_bi.setColor(Color.RED);
        waiyuan_bi.setStrokeWidth(3f); // 设置画笔颜色粗细  用来覆盖细线
        waiyuan_bi.setStyle(Paint.Style.FILL_AND_STROKE);
        waiyuan_path = new Path();


        neiyuan_bi = new Paint(Paint.ANTI_ALIAS_FLAG);
        neiyuan_bi.setColor(Color.WHITE);
        neiyuan_bi.setStrokeWidth(3f);  // 设置画笔颜色粗细 用来覆盖细线
        neiyuan_bi.setStyle(Paint.Style.FILL_AND_STROKE);
        neiyuan_path = new Path();
//        mTransparentCirclePaint.setAlpha(255);  // 105  取值范围为0~255，数值越小越透明。

        zi_bi = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        zi_bi.setColor(Color.BLACK);
        zi_bi.setTextSize(12f);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//         //  画大圆
//        waiyuan_path.addCircle(zx_x,zx_y,100f, Path.Direction.CW);
//        canvas.drawPath(waiyuan_path,waiyuan_bi);


        canvas.drawArc(rectF, 0, 360*0.16f, true, waiyuan_bi); // 画扇形

        waiyuan_bi.setColor(Color.GREEN);
        canvas.drawArc(rectF, 360*0.16f, 360*0.2f, true, waiyuan_bi); // 画扇形

        waiyuan_bi.setColor(Color.BLUE);
        canvas.drawArc(rectF, 360*(0.16f+0.2f), 360*0.3f, true, waiyuan_bi); // 画扇形

        waiyuan_bi.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 360*(0.16f+0.2f+0.3f), 360*(1-0.16f-0.2f-0.3f), true, waiyuan_bi); // 画扇形

        neiyuan_path.addCircle(zx_x,zx_y,70f, Path.Direction.CW);
        canvas.drawPath(neiyuan_path,neiyuan_bi);

    }
}
