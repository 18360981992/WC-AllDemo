package com.ifeng_tech.treasuryyitong.xiaoxituisong.appliction;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zzt on 2018/5/3.
 */

public class ExampleApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
