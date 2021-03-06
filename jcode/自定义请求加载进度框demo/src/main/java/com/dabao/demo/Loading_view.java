package com.dabao.demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by zzt on 2018/6/13.
 */

public class Loading_view extends ProgressDialog {


    public Loading_view(Context context) {
        super(context);
    }

    public Loading_view(Context context, int theme) {
        super(context, theme);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }
    private void init(Context context) {
        setCancelable(true);   // 设置android4.0以后 dialog弹出后会点击屏幕或物理返回键，false==dialog不消失
        setCanceledOnTouchOutside(false);   // dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        setContentView(R.layout.loading);//loading的xml文件

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);

        CircleLoadingView circleLoadingView = findViewById(R.id.circleLoadingView);
        circleLoadingView.startDotAnimator();

    }


    @Override
    public void show() {//开启
        super.show();
    }
    @Override
    public void dismiss() {//关闭
        super.dismiss();
    }
}
