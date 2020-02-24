package com.ifeng_tech.spotmall.fuzhi;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fuzhi(View view){
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", "这里是要复制的文字");
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);

//        jumpToMarket("com.ifeng_tech.treasuryyitong","com.tencent.android.qqdownloader");
    }


    /**
     * 跳转到应用市场
     *
     * @param appPkg
     *            ：上传到应用市场上app的包名,不是本项目的包名
     * @param marketPkg
     *            ：应用市场的包名
     */
    private void jumpToMarket(String appPkg, String marketPkg) {
        Uri uri = Uri.parse("market://details?id=" + appPkg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (marketPkg != null) {// 如果没给市场的包名，则系统会弹出市场的列表让你进行选择。
            intent.setPackage(marketPkg);
        }
        startActivity(intent);
    }
}
