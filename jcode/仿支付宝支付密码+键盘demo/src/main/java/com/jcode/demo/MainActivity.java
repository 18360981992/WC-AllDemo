package com.jcode.demo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jcode.demo.pay_dialog.PayWindwUtils;
import com.jcode.demo.pay_dialog.ResultCallBack;

public class MainActivity extends AppCompatActivity {

    // ll_stroke_bg.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tvNum = (TextView) findViewById(R.id.tv_num);
        findViewById(R.id.btn_out_keyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayWindwUtils.showPayDialog(MainActivity.this, new ResultCallBack() {
                    @Override
                    public void setString(String result, final AlertDialog dialog) {

                        dialog.dismiss();

                        tvNum.setText(result);
                    }
                });
            }
        });
    }
}
