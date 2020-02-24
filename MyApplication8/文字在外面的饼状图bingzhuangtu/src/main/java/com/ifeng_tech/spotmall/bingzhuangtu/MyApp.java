package com.ifeng_tech.spotmall.bingzhuangtu;

import android.app.Application;
import android.content.Context;

/**
 * Created by zzt on 2018/8/7.
 */

public class MyApp extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        getMyContext();
    }

    public void getMyContext(){
        this.context=getApplicationContext();
    }



}
