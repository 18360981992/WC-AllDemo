package com.dabao.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dabao.demo.diyizhong.RefreshLayout;
import com.dabao.demo.diyizhong.ShopView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        if (refreshLayout != null) {
            // 刷新状态的回调
            refreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // 延迟3秒后刷新成功
                    refreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.refreshComplete();
                        }
                    }, 3000);
                }
            });
            ShopView shopView = new ShopView(this);
            refreshLayout.setRefreshHeader(shopView);
//            refreshLayout.autoRefresh();  // 自动刷新
        }

    }

}
