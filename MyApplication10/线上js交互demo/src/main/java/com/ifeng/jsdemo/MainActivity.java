package com.ifeng.jsdemo;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hjhrq1991.library.tbs.DefaultHandler;
import com.hjhrq1991.library.tbs.TbsBridgeWebView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;



public class MainActivity extends AppCompatActivity {

    private TbsBridgeWebView webView;
    public ProgressBar progressBar;
    public PopupWindow po;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // file:///android_asset/test.html
        // http://192.168.1.147:4200/#/phone/product?ReferralCode=5b769c3c6f92e880343dc0d6
        //http://192.168.1.147:4200/#/invitation?ReferralCode=a24b180a
//        webView.loadUrl("http://192.168.1.147:4200/#/invitation?ReferralCode=a24b180a");
//        webView.loadUrl("file:///android_asset/test.html");
//        webView.loadUrl("http://192.168.1.147:4200/#/phone/product?ReferralCode=5b769c3c6f92e880343dc0d6");
        webView.loadUrl("http://www.longmaochina.com/app-h5/longmao-index.html#");

        webView.addJavascriptInterface(new TestInterface(), "android");
    }


    private void initView() {
        webView = (TbsBridgeWebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置javascript弹窗
        // 清缓存和记录，缓存引起的白屏
        webView.clearCache(true);
        webView.clearHistory();
        webView.requestFocus();
        webView.getSettings().setDatabaseEnabled(true);
        // 缓存白屏
        String appCachePath = getApplicationContext().getCacheDir()
                .getAbsolutePath() + "/webcache";
        // 设置 Application Caches 缓存目录
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setDatabasePath(appCachePath);
        webView.getSettings().setAppCacheEnabled(false);// 应用可以有缓存 true false 没有缓存

//        webView.getSettings().setSaveFormData(false);
//        webView.getSettings().setSavePassword(false);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.getSettings().setDisplayZoomControls(false);
//        webView.getSettings().setLoadWithOverviewMode(true);

        webView.setWebChromeClient(new WebChromeClient());//使安卓支持网页的弹出框
        webView.getSettings().setSupportZoom(true);//支持缩放网页
        webView.getSettings().setBuiltInZoomControls(true);//支持缩放网页
        webView.getSettings().setDomStorageEnabled(true); // 打开DOM存储API  这个很重要 是装载js方法执行的关键
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); // 默认不使用缓存！

        //设定支持h5viewport
        webView.getSettings().setUseWideViewPort(true);
        // 设置默认字体大小
        webView.getSettings().setDefaultFontSize(40);
        webView.getSettings().setTextZoom(100);   // 设置页面字体的百分比  用于屏幕适配

        // 自适应屏幕.
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webView.setDefaultHandler(new DefaultHandler());


        // 在不同android版本上出现白屏的情况
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不另跳浏览器
                // 在2.3上面不加这句话，可以加载出页面，在4.0上面必须要加入，不然出现白屏
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    webView.stopLoading();
                    return true;
                }
                return false;
            }
        });

        //加载进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });



    }

    /**
     * Js调用的JavascriptInterface
     */
    public class TestInterface {

        /**
         * 因为安全问题，在Android4.2以后(如果应用的android:targetSdkVersion数值为17+)
         * JS只能访问带有 @JavascriptInterface注解的Java函数。
         *
         *  window.javascriptInterface.circle_of_Friends('我弹了一个Toast  分享到朋友圈')  // 朋友圈
         *
         *  window.javascriptInterface.share_Friends('我弹了一个Toast  分享到好友')  // 好友
         *
         *  window.javascriptInterface.finish_ANDROID()  // 返回
         *
         */

        @JavascriptInterface
        public void circle_of_Friends(final String content) {

            Log.i("wc","cshare_Friends==ontent==="+content);

            Toast.makeText(MainActivity.this, "朋友圈:" + content, Toast.LENGTH_SHORT).show();

                // 异步中重新加载路径
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    webView.loadUrl(content.trim());
//                }
//            });

        }

        @JavascriptInterface
        public void share_Friends(final String content) {

            Log.i("wc","cshare_Friends==ontent==="+content);

            Toast.makeText(MainActivity.this, "好友:" + content, Toast.LENGTH_SHORT).show();

//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    webView.loadUrl(content.trim());
//                }
//            });
        }

        @JavascriptInterface
        public void finish_ANDROID() {
            finish();
        }

        @JavascriptInterface
        public void android_finish() {
            finish();
        }

        @JavascriptInterface
        public void goShar() {
            Log.i("jba","cshare_Friends==ontent===扫描件");
            View fu = findViewById(R.id.fu);
            View zi = View.inflate(getApplicationContext(), R.layout.zibuju, null);
            po = new PopupWindow(zi, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            po.setBackgroundDrawable(new BitmapDrawable());
            po.setOutsideTouchable(true);
            po.showAtLocation(fu, Gravity.CENTER, 0, 0);

            zi.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    po.dismiss();
                }
            });
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
