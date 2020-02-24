package com.ifeng_tech.spotmall.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ifeng_tech.spotmall.myapplication1.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

    private PhotoView photoView;
    private static final String[] IMAGES = {
            "http://pic8.nipic.com/20100701/5290458_114840036316_2.jpg",//海
            "http://pic2.nipic.com/20090424/1468853_230119053_2.jpg",//亭子
            "http://img3.3lian.com/2013/s1/20/d/57.jpg",//白房子
            "http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg",//华
            "http://a0.att.hudong.com/15/08/300218769736132194086202411_950.jpg"};//小路
    private GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        //传人R资源
//        photoView.setImageResource(R.mipmap.ic_launcher);
//
//        //图片的填充类型
//        photoView.setScaleType(ImageView.ScaleType.FIT_XY);
//        // photoView.setZoomable(false);//设置不能缩放
//        //X方向拉伸5倍
////         photoView.setScaleX(5);
//        //设置View的长按事件
//        photoView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                Toast.makeText(MainActivity.this, "你长按了界面", Toast.LENGTH_SHORT).show();
//                //设置尺寸和是否显示动画，1代表原始大小
//                photoView.setScale(1, true);
//
//                return true;
//            }
//        });
        gridview.setAdapter(new MyGridView(this,IMAGES));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("imgs",IMAGES);
                intent.putExtras(bundle);
                intent.putExtra("currentPosition", position);
                startActivity(intent);
            }
        });

    }

    private void initView() {
//        photoView = (PhotoView) findViewById(R.id.photoView);
        gridview = (GridView) findViewById(R.id.gridview);
    }
}
