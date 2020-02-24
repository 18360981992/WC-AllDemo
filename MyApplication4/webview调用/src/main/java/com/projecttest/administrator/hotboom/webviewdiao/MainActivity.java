package com.projecttest.administrator.hotboom.webviewdiao;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.simplejsjavabridge.lib.IJavaCallback2JS;
import com.simplejsjavabridge.lib.SimpleJavaJsBridge;
import com.simplejsjavabridge.lib.annotation.JavaCallback4JS;
import com.simplejsjavabridge.lib.annotation.Param;
import com.simplejsjavabridge.lib.annotation.ParamResponseStatus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private Button invoke_js_exam;
    private Button invoke_js_exam1;
    private Button invoke_js_exam2;
    private Button invoke_js_exam3;
    private Button invoke_js_exam4;
    public static TextView mResultView;

//    String mUrl = "file:///android_asset/bridge_demo.html";
//    String mUrl = "http://m.mv14449315.icoc.bz/index.jsp";
    String mUrl = "http://write.blog.csdn.net/postlist/0/0/enabled/3";

    private ProgressBar progressBar;
    private SimpleJavaJsBridge simpleJavaJsBridge;
    private Button invoke_js_exam5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        context=MainActivity.this;


    }

    public static void setDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("这是什么！")
                .show();
    }

    private  static  Context context;
    private static Toast toast=null;
    public static void setToast(String ss){
        if(toast==null){
            toast=Toast.makeText(context,ss,Toast.LENGTH_SHORT);
        }
        toast.setText(ss);
        toast.show();
    }

    // 初始化id
    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mWebView = (WebView) findViewById(R.id.webView);
        initWebView();

        invoke_js_exam = (Button) findViewById(R.id.invoke_js_exam);
        invoke_js_exam1 = (Button) findViewById(R.id.invoke_js_exam1);
        invoke_js_exam2 = (Button) findViewById(R.id.invoke_js_exam2);
        invoke_js_exam3 = (Button) findViewById(R.id.invoke_js_exam3);
        invoke_js_exam4 = (Button) findViewById(R.id.invoke_js_exam4);
        invoke_js_exam5 = (Button) findViewById(R.id.invoke_js_exam5);
        mResultView = (TextView) findViewById(R.id.result);

        invoke_js_exam.setOnClickListener(this);
        invoke_js_exam1.setOnClickListener(this);
        invoke_js_exam2.setOnClickListener(this);
        invoke_js_exam3.setOnClickListener(this);
        invoke_js_exam4.setOnClickListener(this);
        invoke_js_exam5.setOnClickListener(this);
    }

    // 初始化simp
    private void initData(){

        JavaInterfaces4JS javaInterfaces4JS = new JavaInterfaces4JS();
        simpleJavaJsBridge = new SimpleJavaJsBridge.Builder()
                .addJavaInterface4JS(javaInterfaces4JS)  // 用来添加java提供给js的接口
                .setWebView(mWebView)  //   设置WebView这是必须进行设置的
                .setJSMethodName4Java("_JSNativeBridge._handleMessageFromNative")  //设置js为java唯一暴漏的方法名字
                .setProtocol("niu","receive_msg")  // 设置协议字段，这也是必须的，这个字段主要是为了ios而设置的
                .create();
    }

    // 初始化webview
    private void initWebView() {

        mWebView.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 下方2行代码是指在当前的webview中跳转到新的url
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                mWebViewCallBack.onPageStarted(view, url, favicon);

            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });


        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setLoadsImagesAutomatically(true);

        // settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

        /* 解决空白页问题 */
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);

        // 加载webview的路径
        if (!TextUtils.isEmpty(mUrl)) {
            mWebView.loadUrl(mUrl);
        }

        mWebView.setWebChromeClient(new WebChromeClient(){
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


    // 打印手势动作
    private void showLoading(){
        mResultView.setText("正在调用js的接口......");
    }

    /**
     *   java调用js
     *   有点类似于retrofit  先构建出simpleJavaJsBridge对象
     *   再通过反射 得到一个接口对象  这个接口就是用来定制向js传参的规则
     *
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.invoke_js_exam:  // java 向js 传递普通参数
                showLoading();
                InvokeJS invokJSCommand = simpleJavaJsBridge.createInvokJSCommand(InvokeJS.class);
                invokJSCommand.exam("Holle JS",new IJavaCallback2JS(){
                    @JavaCallback4JS
                    public void test(@ParamResponseStatus("remsgs")String statusMsg, @Param("msg") String msg) {
                        mResultView.setText("js返回信息：状态信息="+statusMsg+"  msg="+msg);
                    }
                });
                break;
            case R.id.invoke_js_exam1:  // java向js传递对象
                showLoading();
                InvokeJS invokJSCommand1 = simpleJavaJsBridge.createInvokJSCommand(InvokeJS.class);
                InvokeJS.City city1 = new InvokeJS.City("江苏", "淮安");

                invokJSCommand1.exam1(city1,new IJavaCallback2JS(){
                    @JavaCallback4JS
                    public void test(@ParamResponseStatus("remsgs")String statusMsg, @Param InvokeJS.City city){
                        mResultView.setText("js返回信息：状态信息="+statusMsg+", cityName="+city.cityName+",  cityProvince="+city.cityProvince);
                    }
                });

                break;
            case R.id.invoke_js_exam2:
                showLoading();
                InvokeJS invokJSCommand2 = simpleJavaJsBridge.createInvokJSCommand(InvokeJS.class);
                InvokeJS.City city2 = new InvokeJS.City("江苏", "淮安");
                invokJSCommand2.exam2(city2,"中国",new IJavaCallback2JS(){
                    @JavaCallback4JS
                    public void test(@ParamResponseStatus("remsgs")String statusMsg, @Param(value = "city") InvokeJS.City city,@Param("contry")String ss){
                        mResultView.setText("js返回信息：状态信息="+statusMsg+", cityName="+city.cityName+",  cityProvince="+city.cityProvince+", contry="+ss);
                    }
                });

                break;
            case R.id.invoke_js_exam3:
                showLoading();
                InvokeJS invokJSCommand3 = simpleJavaJsBridge.createInvokJSCommand(InvokeJS.class);
                InvokeJS.City city3 = new InvokeJS.City("江苏", "淮安");
                invokJSCommand3.exam3(city3,"中国",new IJavaCallback2JS(){
                    @JavaCallback4JS
                    public void test(@ParamResponseStatus("remsgs") String statusMsg, @Param InvokeJS.City city,@Param("contry")String ss){
                        mResultView.setText("js返回信息：状态信息="+statusMsg+", cityName="+city.cityName+", cityProvince="+city.cityProvince+", contry="+ss);
                    }
                });

                break;
            case R.id.invoke_js_exam4:
                showLoading();
                InvokeJS invokJSCommand4 = simpleJavaJsBridge.createInvokJSCommand(InvokeJS.class);
                invokJSCommand4.exam4(new IJavaCallback2JS(){
                    @JavaCallback4JS
                    public void test(@ParamResponseStatus("status")String status,@ParamResponseStatus("remsgs")String statusMsg,@Param("message1")String message1,@Param("message2")String message2){
                        mResultView.setText("js返回信息：状态码="+status+", 状态信息="+statusMsg+", values=="+message1+"=="+message2);
                    }
                });

                break;

            // 传递多个对象  有点问题 待解决
            case R.id.invoke_js_exam5:
                showLoading();
                InvokeJS invokJSCommand5 = simpleJavaJsBridge.createInvokJSCommand(InvokeJS.class);
                InvokeJS.City city5 = new InvokeJS.City("江苏", "淮安");
                InvokeJS.City city6 = new InvokeJS.City("河南", "郑州");
                invokJSCommand5.exam5(city5,city6,new IJavaCallback2JS(){
                    @JavaCallback4JS
                    public void test(@ParamResponseStatus("status")String status,
                                     @ParamResponseStatus("remsgs")String statusMsg,
                                     @Param InvokeJS.City city1,
                                     @Param InvokeJS.City city2){
                        mResultView.setText("js返回信息：状态码="+status+", 状态信息="+statusMsg+", city1Name="+city1.cityName+", city1Province="+city1.cityProvince+"," +
                                "city2Name="+city2.cityName+", city2Province="+city2.cityProvince);
                    }
                });

                break;
        }
    }


}
