package com.ifeng_tech.spotmall.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jwsd.libzxing.OnQRCodeListener;
import com.jwsd.libzxing.QRCodeManager;

public class MainActivity extends AppCompatActivity {

    public TextView main_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_text = (TextView) findViewById(R.id.main_text);
        // 生成
        findViewById(R.id.sheng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sheng_Activity.class);
                startActivity(intent);
            }
        });

        // 扫描
        findViewById(R.id.sao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeManager.getInstance()
                        .with(MainActivity.this)
                        .setReqeustType(1)
//                       .setRequestCode(1001)
                        .scanningQRCode(new OnQRCodeListener() {
                            @Override
                            public void onCompleted(String result) {
                                main_text.setText("扫描结果:"+result);
                            }

                            @Override
                            public void onError(Throwable errorMsg) {
                                main_text.setText("扫描结果:"+errorMsg.toString());
                            }

                            @Override
                            public void onCancel() {
                                main_text.setText("扫描任务取消了");
                            }

                            /**
                             * 当点击手动添加时回调
                             *
                             * @param requestCode
                             * @param resultCode
                             * @param data
                             */
                            @Override
                            public void onManual(int requestCode, int resultCode, Intent data) {
                                Log.i("jiba","点击了手动添加了");
                            }


                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //注册onActivityResult  识别后自动解析结果
        QRCodeManager.getInstance().with(this).onActivityResult(requestCode, resultCode, data);
    }
}
