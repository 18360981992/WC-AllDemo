package com.ifeng_tech.spotmall.myapplication1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zzt on 2018/5/29.
 */

public class MyGridView extends BaseAdapter {
    Context context;
    String[] IMAGES;

    public MyGridView(Context context, String[] IMAGES) {
        this.context = context;
        this.IMAGES = IMAGES;
    }

    @Override
    public int getCount() {
        return IMAGES.length;
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
        if(convertView==null){
            convertView=View.inflate(context,R.layout.item,null);
        }
        ImageView item_img = convertView.findViewById(R.id.item_img);
        Glide.with(context).load(IMAGES[position]).into(item_img);

        return convertView;
    }
}
