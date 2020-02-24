package com.dabao.demo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 滑动条的实现
 *
 * 1. 滑动条跟着列表的移动而移动，需要用到监听，列表滑动的监听，列表移动的距离== 滑动条移动的位置
 *
 * 2. 列表使用gridview就可以实现,但为了监听滑动事件以及滑动距离，我们的grideview还是不够的，所以再包裹一个HorizontalScrollView
 *
 * 3. 画布绘制滑动条与滑动块，慢慢抠就可以抠出来的
 *
 * 4. 将滑动条与监听事件绑定，需要一个移动值，算法  滑动条的移动值 = HorizontalScrollView已移动距离/HorizontalScrollView可移动的最大距离*滑动条可移动的最大距离
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {
    private MyGridView gv;
    private LinearLayout layout;
    private KaiGuan kg;

    private MyHorizontalScrollView myHorizontalScrollView;

    int myHorizontalScrollView_MAX=0;
    public float kg_MAX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setAdapter();

        myHorizontalScrollView.setMyHorizontalScrollView_JieKou(new MyHorizontalScrollView.MyHorizontalScrollView_JieKou() {
            @Override
            public void onScrollChanged_JieKou(int l, int t, int oldl, int oldt) {
                Log.i("jba","l===="+l+",oldl===="+oldl);
                Log.i("jba","myHorizontalScrollView_MAX=="+myHorizontalScrollView_MAX);
                float i = (l - 0.0f) / myHorizontalScrollView_MAX;  // 计算比例
                float v = kg_MAX * i;
                Log.i("jba","v==="+v);
                kg.setYd_zhi(v);  // 赋值
            }
        });

    }

    // 设置适配器
    private void setAdapter() {

        gv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(MainActivity.this, R.layout.item1, null);
                }
                return convertView;
            }
        });
    }

    // 模仿数据源  这里只是借用了集合的长度  数据类型可以自己设置
    List<String> list = new ArrayList<>();
    private void initData() {
        for (int i=0;i<9;i++){
            list.add("item"+i);
        }

        float num1 = list.size() / 2.0f;  // 这里的2.0f是行的意思 一定要是点数类型 并且四舍五入取整  如果这里需要更改的话 切记自定义中也要更改
        final int num = Math.round(num1);
        gv.setNumColumns(num);

        final ViewGroup.LayoutParams layoutParams = gv.getLayoutParams();

        //通过设置监听来获取  控件的宽度
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                int itemWidth = layout.getMeasuredWidth();  // item条目的宽度

                layoutParams.width = itemWidth * num; // gv控件的宽  item的宽*列数
                gv.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件


                /**
                 * 以下是滑动条的初始化设置
                 * 初始化一定要放在视图数中
                 * 并且一定要在网络请求之后  加载适配器之前
                 */
                kg.setInit(list.size(),itemWidth); // 初始化滑动条
                kg_MAX = kg.getkg_MAX();  // 获取移动器最大可移动的距离
                myHorizontalScrollView_MAX=kg.getmyHorizontalScrollView_MAX(); // 获取HorizontalScrollView可滑动的最大值

                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void initView() {
        myHorizontalScrollView = (MyHorizontalScrollView) findViewById(R.id.myHorizontalScrollView);
        gv = (MyGridView) findViewById(R.id.gv);
        layout = (LinearLayout) findViewById(R.id.layout);
        kg = (KaiGuan) findViewById(R.id.kg);

        kg.setBg_Color("#D5E8F2"); // 改变滑动条的背景颜色  #33000000
        kg.setYd_Color("#FF618ADF"); // 改变滑动条中移动器颜色  #777777
    }
}
