package com.ifeng_tech.spotmall.tixing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by zzt on 2018/8/16.
 */

public class MyFragment extends Fragment {
    private ImageView img1;
    private ImageView img2;
    public MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment, container, false);
        activity = (MainActivity) getActivity();

        Bundle bundle = getArguments();
        int[] imgs = bundle.getIntArray("imgs");
        initView(view);

//        Glide.with(this)
//
//                .load(imgs[0])
//
//                .bitmapTransform(new CenterCrop(activity),
//
//                        new MaskTransformation(activity, R.drawable.zuo))
//
//                .into(img1);
//
//        Glide.with(this)
//
//                .load(imgs[1])
//
//                .bitmapTransform(new CenterCrop(activity),
//
//                        new MaskTransformation(activity, R.drawable.zhong))
//
//                .into(img2);


        return view;
    }

    private void initView(View view) {
        img1 = (ImageView) view.findViewById(R.id.img1);
        img2 = (ImageView) view.findViewById(R.id.img2);
    }
}
