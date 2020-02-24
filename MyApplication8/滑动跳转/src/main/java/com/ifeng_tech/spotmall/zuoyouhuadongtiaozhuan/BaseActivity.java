package com.ifeng_tech.spotmall.zuoyouhuadongtiaozhuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

public abstract class BaseActivity extends AppCompatActivity {

    private GestureDetector gue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //这里的第一个参数是上下文，第二个是手势监听器
        gue = new GestureDetector(this, new MyGestureListener());
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//onFling方法的第一个参数是 手指按下的位置， 第二个参数是 手指松开的位置，第三个参数是手指的速度

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float startX = e1.getX();//通过e1.getX（）获得手指按下位置的横坐标
            float endX = e2.getX();//通过e2.getX（）获得手指松开位置的横坐标
            float startY = e1.getY();//通过e1.getY（）获得手指按下位置的纵坐标
            float endY = e2.getY();//通过e2.getY（）获得手指松开的纵坐标
            if ((startX - endX) > 50 && Math.abs(startY - endY) < 200) {
                //(startX - endX) > 50 是手指从按下到松开的横坐标距离大于50
                // Math.abs(startY - endY) < 200 是手指从按下到松开的纵坐标的差的绝对值

                //在这里通过Intent实现界面转跳
            }

            if ((endX - startX) > 50 && Math.abs(startY - endY) <200) {
                //在这里通过Intent实现界面转跳

            }
            //返回值是重点：如果返回值是true则动作可以执行，如果是flase动作将无法执行
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gue.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
