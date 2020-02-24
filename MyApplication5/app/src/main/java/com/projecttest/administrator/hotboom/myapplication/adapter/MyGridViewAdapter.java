package com.projecttest.administrator.hotboom.myapplication.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projecttest.administrator.hotboom.myapplication.CommentOrderActivity;
import com.projecttest.administrator.hotboom.myapplication.R;

import java.util.List;

/**
 * Created by Dell on 2018/4/26.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private List<CommentOrderActivity.MaiYiBean> list;
    private Context context;

    public MyGridViewAdapter(List<CommentOrderActivity.MaiYiBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View gridview_item = View.inflate(context, R.layout.gridviewitem_sxs, null);
         TextView tv= gridview_item.findViewById(R.id.tv_gridview);
         tv.setText(list.get(i).getText());
        if(list.get(i).isFlag()){
            tv.setBackground(context.getResources().getDrawable(R.drawable.textbg2));
        }else{
            tv.setBackground(context.getResources().getDrawable(R.drawable.textbg));
        }


        return gridview_item ;
    }
}
