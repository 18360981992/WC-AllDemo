package com.dabao.demo.dierzhong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dabao.demo.R;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;

public class Main2Activity extends AppCompatActivity implements OnPullListener {

    public AbsRefreshLayout freshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        freshLayout = (AbsRefreshLayout) findViewById(R.id.freshLayout);
        freshLayout.setOnLoadingListener(this);
        freshLayout.setPullLoadEnable(true);
        freshLayout.setPullRefreshEnable(true);
    }

    @Override
    public void onRefresh(AbsRefreshLayout listLoader) {
        Log.i("jba","====走。。。。");
        // 延迟3秒后刷新成功
        freshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                freshLayout.onLoadFinished();
            }
        }, 3000);
    }

    @Override
    public void onLoading(AbsRefreshLayout listLoader) {
        // 延迟3秒后刷新成功
        freshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                freshLayout.onLoadFinished();
            }
        }, 50);
    }
}
