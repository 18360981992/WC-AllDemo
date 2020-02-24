package com.dabao.retrofitmvpdemo.appliction;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.dabao.retrofitmvpdemo.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by Dash on 2017/12/6.
 *
 * G:\Android Demo\ifeng\bigdepotsApp\trunk\android\app\as.jks
 *
 * G:\Android Demo\ifeng\app\as.jks
 */
public class DashApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainId;
    private static DashApplication instance;

    /**
     * 权限标识
     */
    public static int CollPhone=0; // 电话标识
    public static int CAMERA=100; // 相机标识
    public static int WRITE_EXTERNAL_STORAGE=200; // 储存卡标识

    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;


    public static String android;

    // 这是为了打印retrofit的log日志
    //打印retrofit日志
    public static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            LogUtils.i("RetrofitLog","retrofitBack = "+message);
        }
    });

    private List<Activity> oList;//用于存放所有启动的Activity的集合

    @Override
    public void onCreate() {
        super.onCreate();
        //引用图片
        //关于context----http://blog.csdn.net/lmj623565791/article/details/40481055
        context = getApplicationContext();
        oList = new ArrayList<Activity>();
        //初始化handler
        handler = new Handler();
        //主线程的id
        mainId = Process.myTid();


        initScreenSize();

        //获取安卓手机的唯一标识的方法
        android = 'A' + Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        LogUtils.i("jba","MyModel==post===android=="+android);
        // retroft 对log 的初始化
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static DashApplication getInstance() {
        if (instance == null) {
            instance = new DashApplication();
        }
        return instance;
    }

    /**
     * 添加Activity
     */
    public void addActivity_(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!oList.contains(activity)) {
            oList.add(activity);//把当前Activity添加到集合中
        }
    }
    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity_() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : oList) {
            activity.finish();
        }
    }
    /**
     * 对外提供了context
     * @return
     */
    public static Context getAppContext() {
        return context;
    }

    /**
     * 得到全局的handler
     * @return
     */
    public static Handler getAppHanler() {
        return handler;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {
        return mainId;
    }



    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();

        DisplayMetrics metrics = new DisplayMetrics();
        // 取得窗口属性
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    public static String getid() {
        String ID= "";
        ID = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        if(TextUtils.isEmpty(ID)){
            TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            ID = mTelephony.getDeviceId();
        }
        return ID;
    }

}
