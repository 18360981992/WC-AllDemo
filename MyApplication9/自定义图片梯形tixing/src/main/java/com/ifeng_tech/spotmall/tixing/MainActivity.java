package com.ifeng_tech.spotmall.tixing;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.MaskTransformation;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private ViewPager vp;
    int[] imgs = {R.drawable.zuo, R.drawable.zhong};
    int[] imgs1 = {R.drawable.zhong, R.drawable.zuo};
    List<String> list = new ArrayList<>();
    private TextView testTextView;
    private ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Glide.with(this)

                .load(R.drawable.ti_you)

                .bitmapTransform(new CenterCrop(this),

                        new MaskTransformation(this, R.drawable.ti_you))

                .into(img);


        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                MyFragment myFragment = new MyFragment();
                Bundle bundle = new Bundle();
                if (position == 0) {
                    bundle.putIntArray("imgs", imgs);
                } else {
                    bundle.putIntArray("imgs", imgs1);
                }
                myFragment.setArguments(bundle);
                return myFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });


    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        vp = (ViewPager) findViewById(R.id.vp);
        for (int i = 0; i < 15; i++) {
            list.add("item" + i);
        }

        SpUtil.putList(MainActivity.this, "list", list);

        List<String> list1 = SpUtil.getList(this, "list");

        Log.i("jba", "list1==" + list1.toString());

        TextView htmlFormateTextView = (TextView) findViewById(R.id.testTextView);

        String source = "这仅仅是一个測试，測试<u>下划线</u>、<i>斜体字</i>、<font color='red'>红色字</font>的格式";

//        htmlFormateTextView.setText(Html.fromHtml(source));

        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "simkai.ttf");
        htmlFormateTextView.setTypeface(fromAsset);

        htmlFormateTextView.setText(source);
    }
}
