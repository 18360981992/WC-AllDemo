package com.ifeng_tech.spotmall.weixin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MainActivity extends AppCompatActivity {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = WXAPIFactory.createWXAPI(this, "wx86f662ab6c60696c");
        api.registerApp("wx86f662ab6c60696c");

        //在服务端签名
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //假装请求了服务器 获取到了所有的数据
//                WXPayUtils.WXPayBuilder builder = new WXPayUtils.WXPayBuilder();
//                builder.setAppId("123")
//                        .setPartnerId("56465")
//                        .setPrepayId("41515")
//                        .setPackageValue("5153")
//                        .setNonceStr("5645")
//                        .setTimeStamp("56512")
//                        .setSign("54615")
//                        .build().toWXPayNotSign(MainActivity.this,"123");

            }
        });
    }
}
