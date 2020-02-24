package com.ifeng_tech.spotmall.gif;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;


public class MainActivity extends AppCompatActivity {

    private ImageView img;

    //handler发送消息成功的状态码
    private static final int MESSAGE_SUCCESS = 4424;
    //handler发送消息所携带的参数（持续时间）
    private int duration;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("jiba","====什么意思");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Glide.with(MainActivity.this)
                .load(R.drawable.ceshi)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<Integer, GlideDrawable>() {

                    @Override
                    public boolean onException(Exception arg0, Integer arg1,
                                               Target<GlideDrawable> arg2, boolean arg3) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource,
                                                   Integer model, Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        // 计算动画时长
                        GifDrawable drawable = (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            duration += decoder.getDelay(i);
                        }
                        //发送延时消息，通知动画结束
                        //以下两个参数都是 int 型，记得如上的声明
                        handler.sendEmptyMessageDelayed(MESSAGE_SUCCESS,
                                duration);
                        return false;
                    }
                })
                .into(new GlideDrawableImageViewTarget(img, 1));

    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
    }
}
