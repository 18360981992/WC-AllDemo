package com.ifeng_tech.spotmall.jiaobiaojinji;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon(1);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon(2);
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageBitmap(getBitmap(MainActivity.this));


            }
        });
    }


    /**
     * 获取图标 bitmap
     * @param context
     */
    public static synchronized Bitmap getBitmap(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext()
                    .getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();

        return bm;
    }

    /**
     * @param useCode =1、为活动图标 =2 为用普通图标 =3、不启用判断
     */
    private void switchIcon(int useCode) {

        try {
            //要跟manifest的activity-alias 的name保持一致
            String icon_tag = "com.ifeng_tech.spotmall.jiaobiaojinji.icon_tag";
            String icon_tag_1212 = "com.ifeng_tech.spotmall.jiaobiaojinji.icon_tag_1212";

            if (useCode != 3) {

                PackageManager pm = getPackageManager();

                ComponentName normalComponentName = new ComponentName(
                        getBaseContext(),
                        icon_tag);
                //正常图标新状态
                int normalNewState = useCode == 2 ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
                if (pm.getComponentEnabledSetting(normalComponentName) != normalNewState) {//新状态跟当前状态不一样才执行
                    pm.setComponentEnabledSetting(
                            normalComponentName,
                            normalNewState,
                            PackageManager.DONT_KILL_APP);
                }

                ComponentName actComponentName = new ComponentName(
                        getBaseContext(),
                        icon_tag_1212);
                //正常图标新状态
                int actNewState = useCode == 1 ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                        : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
                if (pm.getComponentEnabledSetting(actComponentName) != actNewState) {//新状态跟当前状态不一样才执行

                    pm.setComponentEnabledSetting(
                            actComponentName,
                            actNewState,
                            PackageManager.DONT_KILL_APP);
                }

            }
        } catch (Exception e) {
        }
    }
}
