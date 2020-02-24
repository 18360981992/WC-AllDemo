package com.ifeng_tech.spotmall.ss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/test.html");

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
}
