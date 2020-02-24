package com.ifeng_tech.spotmall.share;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UMConfigure.init(this,"5b70f34c8f4a9d4a740000bd","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
    }

    //友盟相关平台配置。注意友盟官方新文档中没有这项配置，但是如果不配置会吊不起来相关平台的授权界面
    {
        //微信APPID和AppSecret
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com/sina2/callback");
        //QQAPPID和AppSecret
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
