package com.ifeng_tech.spotmall.tu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ebanswers.lsh.RingStatisticsView;

public class MainActivity extends AppCompatActivity {

    private RingStatisticsView id_rsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        id_rsv.setPercentAndColors(new float[]{0.2f,0.2f,0.3f,0.3f},
                new int[]{Color.parseColor("#F9AA28"), Color.parseColor("#009752"), Color.parseColor("#2EC1FB"), Color.parseColor("#FA6723")});
        id_rsv.refresh();
    }

    private void initView() {
        id_rsv = (RingStatisticsView) findViewById(R.id.id_rsv);
    }
}
