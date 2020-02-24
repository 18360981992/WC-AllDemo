package com.ifeng_tech.spotmall.webview;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hjhrq1991.library.tbs.DefaultHandler;
import com.hjhrq1991.library.tbs.TbsBridgeWebView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

public class MainActivity extends AppCompatActivity {

    private TbsBridgeWebView webView;

    String url="http://www.baokuyt.com:8020/#/app/infodetail?id=258&type=new&index=2&plateId=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
    }


    private void initView() {
        webView = (TbsBridgeWebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSaveFormData(false);
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        //设定支持h5viewport
        webView.getSettings().setUseWideViewPort(true);
        // 自适应屏幕.
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.setDefaultHandler(new DefaultHandler());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);
            }
        });

        webView.loadUrl(url);

    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
            webView.goBack();
        else
            finish();
    }
}
