package com.ifeng_tech.spotmall.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private PhotoViewPager view_pager_photo;
    private TextView tv_image_count;
    private TextView tv_save_image_photo;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {

        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition", 0);

        final String[] imgses = intent.getStringArrayExtra("imgs");


        MyImageAdapter adapter = new MyImageAdapter(imgses, this);
        view_pager_photo.setAdapter(adapter);
        view_pager_photo.setCurrentItem(currentPosition, false);
        tv_image_count.setText(currentPosition +1 + "/" + imgses.length);
        view_pager_photo.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tv_image_count.setText(currentPosition + 1 + "/" + imgses.length);
            }
        });
    }

    private void initView() {
        view_pager_photo = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        tv_image_count = (TextView) findViewById(R.id.tv_image_count);
        tv_save_image_photo = (TextView) findViewById(R.id.tv_save_image_photo);
    }
}
