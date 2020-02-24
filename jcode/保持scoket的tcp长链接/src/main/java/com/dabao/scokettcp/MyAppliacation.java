package com.dabao.scokettcp;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2017/5/12.
 */

public class MyAppliacation extends Application {
    public static Context context;
    public static Handler handler;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        handler = new Handler();
        super.onCreate();
    }

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return context;
    }
    /**
     * 得到主线程里面的创建的一个hanlder
     */
    public static Handler getMainThreadHandler() {
        return handler;
    }
}
