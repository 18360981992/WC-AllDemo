package com.ifeng_tech.spotmall.fangzhifubao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by zzt on 2018/7/26.
 */

public class TestAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    Context context;
    List<String> list;

    public TestAdapter(Context context, List<String> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context,R.layout.test_list_item_layout, null);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(list.get(position));
        return convertView;
    }


    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = View.inflate(context,R.layout.header, null);
            holder.text = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        // set header text as first char in name
        CharSequence headerChar = list.get(position).substring(5,7);
        holder.text.setText(headerChar);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        String substring = list.get(position).substring(5, 7);
        Integer integer = Integer.valueOf(substring);
        return integer;
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }
}
