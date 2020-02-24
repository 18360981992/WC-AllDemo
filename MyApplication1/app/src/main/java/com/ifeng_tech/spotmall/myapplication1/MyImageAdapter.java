package com.ifeng_tech.spotmall.myapplication1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ifeng_tech.spotmall.myapplication1.photoview.PhotoView;

import static android.content.ContentValues.TAG;

/**
 * Created by zzt on 2018/5/29.
 */

public class MyImageAdapter extends PagerAdapter {

    String[] imgses;
    Context context;
    private final AppCompatActivity activity;

    public MyImageAdapter(String[] imgses, Context context) {
        this.imgses = imgses;
        this.context = context;
        activity = (AppCompatActivity) context;
    }

    @Override
    public int getCount() {
        return imgses.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imgses[position];
        PhotoView photoView = new PhotoView(context);
        Glide.with(context)
                .load(url)
                .into(photoView);
        container.addView(photoView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                activity.finish();
            }
        });
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
