package com.jcode.demo.pay_dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcode.demo.R;

import java.util.List;

/**
 * Created by zzt on 2018/9/12.
 */

public class PayAdapter extends BaseAdapter {

    private List<PayBean> beanList;
    private Context context;

    public PayAdapter(List<PayBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pay_num, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvNum.setText(beanList.get(position).getNum());
        holder.tvKey.setText(beanList.get(position).getKey());

        if (position == 9 || position == 11) {
            holder.llInclude.setBackgroundColor(context.getResources().getColor(R.color.grayDeep));
        }

        if(position == 11){
            holder.llInclude.setVisibility(View.GONE);
            holder.ll_include_img.setVisibility(View.VISIBLE);
        }else{
            holder.llInclude.setVisibility(View.VISIBLE);
            holder.ll_include_img.setVisibility(View.GONE);
        }

//        else {
//            holder.llInclude.setBackgroundColor(context.getResources().getColor(R.color.grayDeep));
//        }

        return convertView;
    }

    class ViewHolder {
        TextView tvNum;
        TextView tvKey;
        LinearLayout llInclude;
        LinearLayout ll_include_img;
        public ViewHolder(View convertView) {
            tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            tvKey = (TextView) convertView.findViewById(R.id.tv_en_num);
            llInclude = (LinearLayout) convertView.findViewById(R.id.ll_include);
            ll_include_img = (LinearLayout) convertView.findViewById(R.id.ll_include_img);
        }

    }
}
