package com.ifeng_tech.spotmall.jiguangtuisong;

import android.app.Application;
import android.content.Context;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {
    private static final String TAG = "JIGUANG-Example";

    private static Context context = null;
    @Override
    public void onCreate() {    	     
    	 Logger.d(TAG, "[ExampleApplication] onCreate");
         super.onCreate();

//         JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
//         JPushInterface.init(this);     		// 初始化 JPush
        context=getApplicationContext();
    }

    public static Context  getAppContext(){
        return context;
    }

}
