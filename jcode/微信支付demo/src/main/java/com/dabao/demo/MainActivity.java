package com.dabao.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String appid="wx531348a74bfcb573";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //在服务端签名
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //假装请求了服务器 获取到了所有的数据
                WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
                builder.setAppId(appid) // 应用id  wx531348a74bfcb573
                        .setPartnerId("56465") // 商家id
                        .setPrepayId("41515") // 订单号
                        .setPackageValue("5153") //  扩展字段 可有可无
                        .setNonceStr("5645")  // 随机字符串
                        .setTimeStamp("56512")  // 时间挫
                        .setSign("54615")  // 签名
                        .build().toWXPayNotSign(MainActivity.this,appid);
            }
        });
    }
}
