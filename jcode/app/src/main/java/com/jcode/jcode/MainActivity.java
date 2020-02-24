package com.jcode.jcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.jiguang.analytics.android.api.CalculateEvent;
import cn.jiguang.analytics.android.api.CountEvent;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSetting;

    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JAnalyticsInterface.setDebugMode(true);
        JAnalyticsInterface.init(this);
        initView();
    }

    private void initView() {
        TextView mImei = (TextView) findViewById(R.id.tv_imei);
        String udid = ExampleUtil.getImei(getApplicationContext(), "");
        if (null != udid) mImei.setText("IMEI: " + udid);

        TextView mAppKey = (TextView) findViewById(R.id.tv_appkey);
        String appKey = ExampleUtil.getAppKey(getApplicationContext());
        if (null == appKey) appKey = "AppKey异常";
        mAppKey.setText("AppKey: " + appKey);


        String packageName = getPackageName();
        TextView mPackage = (TextView) findViewById(R.id.tv_package);
        mPackage.setText("PackageName: " + packageName);


        String versionName = ExampleUtil.GetVersion(getApplicationContext());
        TextView mVersion = (TextView) findViewById(R.id.tv_version);
        mVersion.setText("Version: " + versionName);

        mSetting = (Button) findViewById(R.id.setting);
        mSetting.setOnClickListener(this);
        findViewById(R.id.analytics_example).setOnClickListener(this);
        findViewById(R.id.analytics_example_replace).setOnClickListener(this);
        findViewById(R.id.analytics_example_showhide).setOnClickListener(this);
        findViewById(R.id.analytics_example_account).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
//            case R.id.setting:
//
////                intent = new Intent(MainActivity.this, SetActivity.class);
////                startActivity(intent);
//                break;
            case R.id.analytics_example:

                // 自定义计数事件
                CountEvent countEvent = new CountEvent("1");  // 事件ID ：1
                countEvent.addKeyValue("extra4", "3" );  //触发者 ：extra4  响应内容：2
                JAnalyticsInterface.onEvent(this, countEvent);

                // 自定义计算事件
                CalculateEvent cEvent = new CalculateEvent("2",0);
                cEvent.addKeyValue("计算", "2" );
                JAnalyticsInterface.onEvent(this, cEvent);

//                intent = new Intent(MainActivity.this, AnalyticsMainActivity.class);
//                startActivity(intent);
                break;
//            case R.id.analytics_example_replace:
//                intent = new Intent(MainActivity.this, ReplaceActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.analytics_example_showhide:
//                intent = new Intent(MainActivity.this, ShowHideActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.analytics_example_account:
//                intent = new Intent(MainActivity.this, AccountExampleActivity.class);
//                startActivity(intent);
//                break;
        }
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        JAnalyticsInterface.onPageStart(getApplicationContext(),this.getClass().getCanonicalName());
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
        JAnalyticsInterface.onPageEnd(getApplicationContext(),this.getClass().getCanonicalName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
