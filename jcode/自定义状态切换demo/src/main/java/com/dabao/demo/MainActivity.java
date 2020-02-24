package com.dabao.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SwitchButton switchButton = (SwitchButton) findViewById(R.id.switch_button);

        // 动态改变状态  传值 0 or 1
        switchButton.setDefaultStatus(0);

        switchButton.setOnSwitchListener(new SwitchButton.OnSwitchListener() {
            @Override
            public void onSwitchChange() {

                Toast.makeText(MainActivity.this,"状态改变==="+switchButton.getCurrentStatus(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
