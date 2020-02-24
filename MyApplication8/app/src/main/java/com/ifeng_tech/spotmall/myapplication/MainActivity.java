package com.ifeng_tech.spotmall.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button btn;
    private WebView webView;
    String url = "file:///android_asset/test.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        //Android原生方式
//        BadgeUtils.setBadgeCount(MainActivity.this,20);
//        //第三方设置角标
//        BadgeCountUtils.setBadgeCount(MainActivity.this, 20);

//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                changeLuncher("com.ifeng_tech.spotmall.myapplication.newsLuncherActivity");
//            }
//        });
        webView.loadUrl(url);//加载本地路径文件，，url
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 下方2行代码是指在当前的webview中跳转到新的url
                view.loadUrl(url);
                return true;
            }
        });//不去跳转到别的浏览器

        WebSettings settings = webView.getSettings();//获得设置页面的权限

        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置javascript弹窗

        settings.setJavaScriptEnabled(true);//是安卓支持js脚本

        settings.setSupportZoom(true);//支持缩放网页

        webView.setWebChromeClient(new WebChromeClient());//使安卓支持网页的弹出框

    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        webView = (WebView) findViewById(R.id.webView);
    }


//    private void changeLuncher(String name) {
//        PackageManager pm = getPackageManager();
//        pm.setComponentEnabledSetting(getComponentName(),
//                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//        pm.setComponentEnabledSetting(new ComponentName(MainActivity.this, name),
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
//
//        //Intent 重启 Launcher 应用
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        List<ResolveInfo> resolves = pm.queryIntentActivities(intent, 0);
//        for (ResolveInfo res : resolves) {
//            if (res.activityInfo != null) {
//                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//                am.killBackgroundProcesses(res.activityInfo.packageName);
//            }
//        }
//    }

}
