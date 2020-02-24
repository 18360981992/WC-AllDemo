package com.dabao.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;

    String[] list={"title1","title2","title3","title4","title5","title6","title7","title8","title9","title10","title11","title12"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        for (int i = 0; i < list.length; i++) {
            //添加tab
            tabLayout.addTab(tabLayout.newTab().setText(list[i]));

            TabLayout.Tab tab = tabLayout.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tab_text).setSelected(true);//第一个tab被选中
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
            textView.setText(list[i]);//设置tab上的文字
        }
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }
}
