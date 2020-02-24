package com.ifeng_tech.spotmall.jiguangtuisong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {
    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JPushInterface.init(getApplicationContext());
        registerMessageReceiver();


        TextView mImei = (TextView) findViewById(R.id.tv_imei);
        String udid =  ExampleUtil.getImei(getApplicationContext(), "");
        if (null != udid) mImei.setText("IMEI: " + udid);

        TextView mAppKey = (TextView) findViewById(R.id.tv_appkey);
        String appKey = ExampleUtil.getAppKey(getApplicationContext());
        if (null == appKey) appKey = "AppKey异常";
        mAppKey.setText("AppKey: " + appKey);

        TextView mRegId = (TextView) findViewById(R.id.tv_regId);
        mRegId.setText("RegId:");

        String packageName =  getPackageName();
        TextView mPackage = (TextView) findViewById(R.id.tv_package);
        mPackage.setText("PackageName: " + packageName);

        String deviceId = ExampleUtil.getDeviceId(getApplicationContext());
        TextView mDeviceId = (TextView) findViewById(R.id.tv_device_id);
        mDeviceId.setText("deviceId:" + deviceId);

        String versionName =  ExampleUtil.GetVersion(getApplicationContext());
        TextView mVersion = (TextView) findViewById(R.id.tv_version);
        mVersion.setText("Version: " + versionName);

    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
    //for receive customer msg from jpush server   jpush_notification_icon.png
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {


        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);

                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
//                    setCostomMsg(showMsg.toString());
                    Log.i("jiba","MessageReceiver====,messge==="+messge+",extras==="+extras);

//                    BasicPushNotificationBuilder builder2 = new BasicPushNotificationBuilder(ExampleApplication.getAppContext());
//                    builder2.statusBarDrawable = R.drawable.raw_1500085327;
//                    builder2.notificationFlags = Notification.FLAG_AUTO_CANCEL; // 设置为自动消失
//                    builder2.notificationDefaults = Notification.DEFAULT_SOUND
//                            | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS; // 设置为铃声与震动都要
//                    JPushInterface.setDefaultPushNotificationBuilder(builder2);
//                    JPushInterface.setPushNotificationBuilder(2, builder2);
                }
            } catch (Exception e){
            }
        }
    }

//    private EditText msgText;
//    private void setCostomMsg(String msg){
//        if (null != msgText) {
//            msgText.setText(msg);
//            msgText.setVisibility(android.view.View.VISIBLE);
//        }
//    }
}
