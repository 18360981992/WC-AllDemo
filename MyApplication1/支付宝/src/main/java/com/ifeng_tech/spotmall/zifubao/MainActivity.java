package com.ifeng_tech.spotmall.zifubao;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.ifeng_tech.spotmall.zifubao.alipay.PayResult;
import com.ifeng_tech.spotmall.zifubao.base.BaseMVPActivity;
import com.ifeng_tech.spotmall.zifubao.bean.LoginBean;
import com.ifeng_tech.spotmall.zifubao.interfaces.MyInterfaces;
import com.ifeng_tech.spotmall.zifubao.presenter.MyPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseMVPActivity<MainActivity,MyPresenter<MainActivity>> {

    @Override
    public MyPresenter<MainActivity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }


    public void btn1(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("userName","18360981992");
        map.put("password","1234567");
        map.put("loginType","0");
        myPresenter.postPreContent(APIs.login, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                if(loginBean.getCode().equals("2000")){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void shibai(String ss) {
                Toast.makeText(MainActivity.this,ss,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    //使用返回的支付结果字符串构建一个支付结果对象
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&docType=1) 建议商户依赖异步通知
                     */
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    //----------app端拿到支付宝同步返回的结果,需要发送给服务器端,服务器端经过验证签名和解析支付结果,然后给app端返回最终支付的结果

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {

                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.equals(resultStatus, "4000")){
                        Toast.makeText(MainActivity.this, "系统繁忙", Toast.LENGTH_SHORT).show();

                    } else{
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(MainActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }

                    /**
                     * 不管订单支付成功或者失败 订单是已经生成了 如果成功 跳转订单列表的已支付,,,如果失败跳转的是订单列表的待支付
                     */

                    break;
                }
                default:
                    break;
            }
        }

    };

    String ss="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016091500514662&biz_content=%7B%22out_trade_no%22%3A%22P00000000502018061111423043018603%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22APP%E7%AB%AF%E5%85%85%E5%80%BC%22%2C%22total_amount%22%3A%221000.2%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&sign=YHpMAhq3Su1adEeF9wZfh3n09dwnbjkIQB6HrcDFyYSuCPGSZ4K%2BQ0rWpGmfpVRqzyHqBOUp63v8vEZR0PBN9RXiyjtRljZ5ZY4XvbL76NyeGVs3ZKcOTWpHZkqsI0x7ZTm7h1yYAhyW9shwVj3n%2B%2BUvB2SAW0k5ZOcf9eTclLI99Z6DVwNACOWrXCz8Gp%2FWPKYR2gMYnU14KLa2xODMQ2xTWtVuTpokt7NwNI6l2Tp5WcR4aXhy73f0FABsaZSIfq05EGPBihM69HzF3bTT9XjTgdMaCJf%2Fs7uV3KJsfJHM1YELlznghUN58h7ZRBNXMDYshLCxWuiCJdr3ir%2FZJg%3D%3D&sign_type=RSA2&timestamp=2018-06-11+11%3A43%3A20&version=1.0";
    public void btn(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("amount","0.2");
        map.put("paymentPlatform","1");

        myPresenter.postPreContent(APIs.personalUserRecharge, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        String message = (String) jsonObject.get("message");
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 构造PayTask 对象
                                PayTask alipay = new PayTask(MainActivity.this);
                                // 调用支付接口，获取支付结果
                                String result = alipay.pay(ss, true);

                                Log.e("------",result);

                                Message msg = new Message();
                                msg.what = 0;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        }).start();


                    }else{
                        String message = (String) jsonObject.get("message");
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                Toast.makeText(MainActivity.this,ss,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
