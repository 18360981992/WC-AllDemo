package com.dabao.gridviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zzt on 2018/10/30.
 Home_TemplateOrMondular
 */

public class ExAdapter extends BaseAdapter {

    Context context;
    List<Bean> list;

    public ExAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_one,null);
        }
        TextView title1 = convertView.findViewById(R.id.title1);
        title1.setText(list.get(position).getTitle());


        GridView myGridView = convertView.findViewById(R.id.myGridView);

        myGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                int size = list.get(position).getList().size();
                int i = size % 3;
                if(i==0){
                    return size;
                }else{
                    int i1 = size / 3;
                    int i2 = 3 * (i1 + 1);
                    return i2;
                }
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
            public View getView(int position1, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=View.inflate(context, R.layout.grid_item,null);
                }
                ImageView grid_img = convertView.findViewById(R.id.grid_img);
                TextView grid_name = convertView.findViewById(R.id.grid_name);
                View view1 = convertView.findViewById(R.id.view1);
                View view2 = convertView.findViewById(R.id.view2);

                if(position1%3==1){
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                }else{
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.INVISIBLE);
                }

                if(position1<list.get(position).getList().size()){
                    grid_img.setImageResource(list.get(position).getList().get(position1).getImg());
                    grid_name.setText(list.get(position).getList().get(position1).getName());
                }else{
                    grid_img.setBackgroundResource(0);
                    grid_name.setText("");
                }

                return convertView;
            }
        });

        return convertView;
    }
}
