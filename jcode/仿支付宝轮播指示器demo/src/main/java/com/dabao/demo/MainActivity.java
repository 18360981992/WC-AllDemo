package com.dabao.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XBanner homepage_XBanner;
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<ImageView> images;
    public LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        list.clear();
        list.add(R.drawable.c1);
        list.add(R.drawable.c2);
        list.add(R.drawable.c3);
        list.add(R.drawable.c4);

        homepage_XBanner.setData(list,null);//设置数据源

        homepage_XBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(MainActivity.this).load(list.get(position)).into((ImageView) view);
            }
        });

        initDoc(list);  // 设置指示器

        homepage_XBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int j=0;j<images.size();j++){
                    if (j == position%images.size()){
                        images.get(j).setImageResource(R.drawable.xbanner_selected);
                    }else {
                        images.get(j).setImageResource(R.drawable.xbanner_noraml);
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initDoc(List<Integer> list) {
        //1.需要一个集合记录一下小圆点的imageView控件
        images = new ArrayList<ImageView>();
        //2...linearLayout上面的视图清空一下再去添加
        linearLayout.removeAllViews();


        for (int i=0;i<list.size();i++){
            ImageView imageView = new ImageView(MainActivity.this);


            if (i==0){
                imageView.setImageResource(R.drawable.xbanner_selected);
            }else {
                imageView.setImageResource(R.drawable.xbanner_noraml);
            }


            //添加到集合去
            images.add(imageView);
            //添加到线性布局上


            //这是布局参数,,刚开始小圆点之间没有距离,所以使用java代码指定宽度高度,并且指定小圆点之间的距离
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);


            params.setMargins(5,0,5,0);


            linearLayout.addView(imageView,params);
        }


    }

    private void initView() {
        homepage_XBanner = (XBanner) findViewById(R.id.homepage_XBanner);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }
}
